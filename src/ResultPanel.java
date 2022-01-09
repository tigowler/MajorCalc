import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ResultPanel{
    JPanel mainPanel, centerPanel, chartAreaPanel, threadPanel;
    JButton gradeToResultBtn, paintResultBtn;
    GridBagConstraints con = new GridBagConstraints();
    GradeThread gt1, gt2, gt3, gt4;
    JLabel[] grades = {new JLabel("1학년"), new JLabel("2학년"), new JLabel("3학년"), new JLabel("4학년")};
    JLabel l1, l2, l3, l4, chartTitle;

    CalcRef calc = new CalcRef();
    ResultTablePanel timeTablePanel = new ResultTablePanel();
    ResultScoreTablePanel scoreTablePanel = new ResultScoreTablePanel();
    IconPanel iconPanel = new IconPanel();
    DefaultChartPanel defaultChartPanel = new DefaultChartPanel();
    ChartPanel chartPanel;

    public ResultPanel(){
        mainPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridBagLayout());
        threadPanel = new JPanel(new GridBagLayout());
        threadPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "계산 진행율"));
        chartAreaPanel = new JPanel(new BorderLayout());
        l1 = new JLabel("0%");
        l2 = new JLabel("0%");
        l3 = new JLabel("0%");
        l4 = new JLabel("0%");
        chartTitle = new JLabel("학년별 성적과 이수학점");
        chartTitle.setHorizontalAlignment(JLabel.CENTER);
        setLabelAlignment();
        setThreadPanel();
        gradeToResultBtn = new JButton("▶");
        paintResultBtn = new JButton("Show Result");

        chartAreaPanel.add(chartTitle, BorderLayout.NORTH);
        chartAreaPanel.add(defaultChartPanel, BorderLayout.CENTER);

        con.weighty = 0.5;
        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(threadPanel, con);
        con.gridy = 1;
        con.weighty = 4;
        centerPanel.add(chartAreaPanel, con);
        con.gridy = 2;
        con.weighty = 1.5;
        centerPanel.add(scoreTablePanel, con);
        con.gridy = 3;
        centerPanel.add(timeTablePanel, con);
        con.weighty = 0.5;
        con.gridy = 4;
        centerPanel.add(iconPanel, con);
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

    public boolean paintThread(GradeTablePanel[] tablePanels) throws NoScoreSelectedException{
        //성적이 입력되지 않은 강의가 있는지 확인
        for (int i=0; i<tablePanels.length; i++){
            for (int j=0; j<tablePanels[i].table.getRowCount(); j++){
                if (tablePanels[i].table.getValueAt(j, 0).toString().equals("Click")){
                    throw new NoScoreSelectedException(i+1);
                }
            }
        }

        gt1 = new GradeThread(tablePanels[0].table, l1, threadPanel,1);
        gt2 = new GradeThread(tablePanels[1].table, l2, threadPanel,2);
        gt3 = new GradeThread(tablePanels[2].table, l3, threadPanel,3);
        gt4 = new GradeThread(tablePanels[3].table, l4, threadPanel,4);

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

    //GradeThread에 접근해 필요한 table, chart에 필요한 변수들 계산
    public void calcScoreAndTime(String stdGrade, String stdMajor){
        //값 모두 가져와 저장하기
        ArrayList<Integer> allRequiredTime = new ArrayList<>();
        ArrayList<Integer> allTotalTime = new ArrayList<>();
        ArrayList<String> scorePerGrade = new ArrayList<>();

        saveFigurePerGrade(gt1, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt2, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt3, allRequiredTime, allTotalTime, scorePerGrade);
        saveFigurePerGrade(gt4, allRequiredTime, allTotalTime, scorePerGrade);

        //학점 table에 입력
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

        timeTablePanel.setTimesToTable(gradRequiredTime, gradTotalTime, stdRequiredTime, stdTotalTime);

        //성적 table에 입력
        scoreTablePanel.setScoreToTable(scorePerGrade, allTotalTime);

        //chart에 성적 입력
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i=0; i<scorePerGrade.size(); i++){
            scores.add((int)(Double.parseDouble(scorePerGrade.get(i))*100));
        }

        ArrayList<Integer> times = new ArrayList<>();
        for (int time : allTotalTime){
            times.add(time*15);
        }

        chartPanel = new ChartPanel(scores, times);
        chartAreaPanel.add(chartPanel, BorderLayout.CENTER);
    }

    private void saveFigurePerGrade(GradeThread gt, ArrayList<Integer> allRequiredTime, ArrayList<Integer> allTotalTime, ArrayList<String> scorePerGrade) {
        allRequiredTime.add(gt.getRequiredTime());
        allTotalTime.add(gt.getTotalTime());
        scorePerGrade.add(gt.getStringTotalScore());
    }

}
