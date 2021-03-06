import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GradeThread extends Thread{
    JTable table;
    JLabel percent;
    JPanel panel;
    private ArrayList<String> scores;
    private ArrayList<Integer> times;
    private ArrayList<String> majors;
    private int requiredTime, selectiveTime, passTime, totalTime;
    private double totalScore;
    CalcRef crf = new CalcRef();
    private DecimalFormat form = new DecimalFormat("#.##");
    int name;

    public GradeThread(JTable table, JLabel percent, JPanel panel, int name){
        this.table = table;
        this.percent = percent;
        percent.setText("0%");
        this.panel = panel;
        scores = new ArrayList<>();
        times = new ArrayList<>();
        majors = new ArrayList<>();
        requiredTime = 0;
        selectiveTime = 0;
        passTime = 0;
        totalTime =0;
        totalScore = 0.0;
        this.name = name;
    }

    @Override
    public void run() {
        //1) 성적, 학점, 전공구분 가져오기
        if (table.getRowCount()==0){
            System.out.println(name+"학년에 입력된 과목이 없습니다.");
            return;
        }
        for (int i=0; i<table.getRowCount(); i++){
            scores.add(table.getValueAt(i, 0).toString());
            times.add(Integer.parseInt(table.getValueAt(i, 4).toString().substring(0, 1)));
            majors.add(table.getValueAt(i, 2).toString());
        }

        //2) 이수한 전공필수/선택 학점과 Pass 학점 계산
        for (int i=0; i<majors.size(); i++){
            if (majors.get(i).equals("전공필수")){
                requiredTime+=times.get(i);
            } else if (majors.get(i).equals("전공선택")){
                selectiveTime+=times.get(i);
            }

            if (scores.get(i).equals("P")){
                passTime+=times.get(i);
            }
        }

        //3) 총 이수학점 계산
        totalTime = requiredTime+selectiveTime;

        //4) 성적 계산
        double tmpSum=0;
        for (int i=0; i<scores.size(); i++){
            tmpSum+=times.get(i)*crf.getScore(scores.get(i));
        }
        totalScore = tmpSum/(totalTime-passTime);
        percent.setText("100%");
        System.out.println(name+"학년 계산완료");
    }

    public String getStringTotalScore(){
        return form.format(totalScore);
    }

    public int getRequiredTime(){
        return requiredTime;
    }

    public int getTotalTime(){
        return totalTime;
    }
}