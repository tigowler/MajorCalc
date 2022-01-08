import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GradeThread extends Thread{
    int x, y;
    int speed;

    JPanel panel;
    JTable table;
    private ArrayList<String> scores;
    private ArrayList<Integer> times;
    private ArrayList<String> majors;
    private int requiredTime, selectiveTime, passTime, totalTime;
    private double totalScore;
    CalcRef crf = new CalcRef();
    private DecimalFormat form = new DecimalFormat("#.##");

    public GradeThread(int x, int y, JTable table, JPanel panel){
        this.x = x;
        this.y = y;
        this.table = table;
        this.panel = panel;
        scores = new ArrayList<>();
        times = new ArrayList<>();
        majors = new ArrayList<>();
        requiredTime = 0;
        selectiveTime = 0;
        passTime = 0;
        totalTime =0;
        totalScore = 0.0;
    }

    @Override
    public void run() {
        speed = (int)(Math.random()*50);
        //1) 성적, 학점, 전공구분 가져오기
        for (int i=0; i<table.getRowCount(); i++){
            if (table.getValueAt(i, 0).toString().equals("Click")){
                System.out.println("성적이 입력되지 않은 과목이 있습니다."+this.getState());
                this.stop();
                return;
            }
            scores.add(table.getValueAt(i, 0).toString());
            times.add(Integer.parseInt(table.getValueAt(i, 4).toString().substring(0, 1)));
            majors.add(table.getValueAt(i, 2).toString());
        }
        x+=100;
        panel.repaint();
        try{
            sleep(speed*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speed = (int)(Math.random()*50);
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
        x+=100;
        panel.repaint();
        try{
            sleep(speed*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speed = (int)(Math.random()*50);
        //3) 총 이수학점 계산
        totalTime = requiredTime+selectiveTime;

        x+=100;
        panel.repaint();
        try{
            sleep(speed*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speed = (int)(Math.random()*50);
        //4) 성적 계산
        double tmpSum=0;
        for (int i=0; i<scores.size(); i++){
            tmpSum+=times.get(i)*crf.getScore(scores.get(i));
        }
        totalScore = tmpSum/(totalTime-passTime);
        System.out.println(this.getState());
    }

    public String getStringTotalScore(){
        return form.format(totalScore);
    }

    public int getRequiredTime(){
        return requiredTime;
    }

    public int getSelectiveTime(){
        return selectiveTime;
    }

    public int getTotalTime(){
        return totalTime;
    }
}