import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultPanel extends Thread{
    JPanel mainPanel, centerPanel, threadPanel, chartPanel;
    JButton gradeToResultBtn, paintResultBtn;
    GridBagConstraints con = new GridBagConstraints();
    GradeThread gt1, gt2, gt3, gt4;
    JLabel[] grades = {new JLabel("1학년"), new JLabel("2학년"), new JLabel("3학년"), new JLabel("4학년")};
    JLabel l1, l2, l3, l4;

    CalcRef calc = new CalcRef();
    ResultTablePanel tablePanel = new ResultTablePanel();

    public ResultPanel(){
        mainPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridBagLayout());
        threadPanel = new JPanel(new GridBagLayout());
        l1 = new JLabel("0%");
        l2 = new JLabel("0%");
        l3 = new JLabel("0%");
        l4 = new JLabel("0%");
        setLabelAlignment();
        setThreadPanel();
        gradeToResultBtn = new JButton("▶");
        paintResultBtn = new JButton("Show Result");
        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(threadPanel, con);
        con.gridy = 1;
        centerPanel.add(tablePanel, con);
        mainPanel.add(gradeToResultBtn, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void setLabelAlignment() {
        for (JLabel l: grades){
            l.setHorizontalAlignment(JLabel.CENTER);
        }
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);
        l4.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setThreadPanel() {
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.weighty = 1;
        con.gridy = 0;
        for (int i=0; i<grades.length; i++){
            con.gridx = i;
            threadPanel.add(grades[i], con);
        }
        con.gridy = 1;
        con.gridx = 0;
        threadPanel.add(l1, con);
        con.gridx = 1;
        threadPanel.add(l2, con);
        con.gridx = 2;
        threadPanel.add(l3, con);
        con.gridx = 3;
        threadPanel.add(l4, con);
    }

    public boolean paintThread(GradeTablePanel[] tablePanels){
        gt1 = new GradeThread(tablePanels[0].table, l1, threadPanel);
        gt2 = new GradeThread(tablePanels[1].table, l2, threadPanel);
        gt3 = new GradeThread(tablePanels[2].table, l3, threadPanel);
        gt4 = new GradeThread(tablePanels[3].table, l4, threadPanel);

        gt1.start();
        gt2.start();
        gt3.start();
        gt4.start();

        try {
            gt1.join();
            gt2.join();
            gt3.join();
            gt4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void calcScoreAndTime(String stdGrade, String stdMajor){
        //GradeThread에 접근해 필요한 table, chart에 필요한 변수들 계산
        System.out.println("도착했어@_@");

        //값 모두 가져와 저장하기
        ArrayList<Integer> allRequiredTime = new ArrayList<>();
        ArrayList<Integer> allTotalTime = new ArrayList<>();
        ArrayList<String> scorePerGrade = new ArrayList<>();

        saveFigurePerGrade(gt1, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt2, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt3, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt4, allRequiredTime, allTotalTime, scorePerGrade);

        //table에 입력
        int gradRequiredTime = calc.getRequiredCourse(stdGrade, stdMajor);
        int gradTotalTime = calc.getSumCourses(stdGrade, stdMajor);
        int stdRequiredTime = 0;
        for (int time: allRequiredTime){
            stdRequiredTime+=time;
        }
        int stdTotalTime = 0;
        for (int time : allTotalTime){
            stdTotalTime+=time;
        }

        tablePanel.setTimesToTable(gradRequiredTime, gradTotalTime, stdRequiredTime, stdTotalTime);

    }

    private void saveFigurePerGrade(GradeThread gt, ArrayList<Integer> allRequiredTime, ArrayList<Integer> allTotalTime, ArrayList<String> scorePerGrade) {
        allRequiredTime.add(gt.getRequiredTime());
        allTotalTime.add(gt.getTotalTime());
        scorePerGrade.add(gt.getStringTotalScore());
    }

}
