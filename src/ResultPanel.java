import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultPanel{
    JPanel mainPanel, centerPanel, chartAreaPanel;
    JButton gradeToResultBtn, paintResultBtn;
    GridBagConstraints con = new GridBagConstraints();
    GradeThread gt1, gt2, gt3, gt4;
    JLabel chartTitle;

    CalcRef calc = new CalcRef();
    ResultTablePanel timeTablePanel = new ResultTablePanel();
    ResultScoreTablePanel scoreTablePanel = new ResultScoreTablePanel();
<<<<<<< HEAD
    ChartPanel chartPanel = new ChartPanel();
    IconPanel iconPanel = new IconPanel();
=======
    DefaultChartPanel defaultChartPanel = new DefaultChartPanel();
    ChartPanel chartPanel;
>>>>>>> parent of 86618e7 (complete default)

    public ResultPanel(){
        mainPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridBagLayout());
<<<<<<< HEAD
=======
        threadPanel = new JPanel(new GridBagLayout());
>>>>>>> parent of 86618e7 (complete default)
        chartAreaPanel = new JPanel(new BorderLayout());
        chartTitle = new JLabel("학년별 성적과 이수학점");
        chartTitle.setHorizontalAlignment(JLabel.CENTER);
        gradeToResultBtn = new JButton("▶");
        paintResultBtn = new JButton("Show Result");

        chartAreaPanel.add(chartTitle, BorderLayout.NORTH);
        chartAreaPanel.add(chartPanel, BorderLayout.CENTER);

<<<<<<< HEAD
        con.fill = GridBagConstraints.BOTH;
        con.gridx = 0;
        con.gridy = 0;
        con.weighty = 4;
        centerPanel.add(chartAreaPanel, con);
        con.gridy = 1;
        con.weighty = 1.5;
        centerPanel.add(scoreTablePanel, con);
        con.gridy = 2;
        centerPanel.add(timeTablePanel, con);
        con.weighty = 0.5;
        con.gridy = 3;
        centerPanel.add(iconPanel, con);
=======
        con.weighty = 0.3;
        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(threadPanel, con);
        con.weighty = 1.5;
        con.gridy = 1;
        centerPanel.add(timeTablePanel, con);
        con.weighty = 3;
        con.gridy = 2;
        centerPanel.add(chartAreaPanel, con);
        con.gridy = 3;
        centerPanel.add(scoreTablePanel, con);
>>>>>>> parent of 86618e7 (complete default)
        mainPanel.add(gradeToResultBtn, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public boolean paintThread(GradeTablePanel[] tablePanels){
        gt1 = new GradeThread(tablePanels[0].table, 1);
        gt2 = new GradeThread(tablePanels[1].table, 2);
        gt3 = new GradeThread(tablePanels[2].table, 3);
        gt4 = new GradeThread(tablePanels[3].table, 4);

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

        ChartPanel newChartPanel = new ChartPanel(scores, times);
        chartAreaPanel.add(newChartPanel, BorderLayout.CENTER);
    }

    private void saveFigurePerGrade(GradeThread gt, ArrayList<Integer> allRequiredTime, ArrayList<Integer> allTotalTime, ArrayList<String> scorePerGrade) {
        allRequiredTime.add(gt.getRequiredTime());
        allTotalTime.add(gt.getTotalTime());
        scorePerGrade.add(gt.getStringTotalScore());
    }

}
