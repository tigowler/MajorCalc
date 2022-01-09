import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TestMain {
    static SelectPanel selectPanel;
    static GradePanel gradePanel;
    static ResultPanel resultPanel;
    static JFrame mainFrame;
    static boolean isSameLecture = false;

    public static void main(String[] args) {
        mainFrame = new JFrame("SM IT MAJOR CALC");
        mainFrame.setLayout(new GridLayout(1, 3));
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setExtendedState(MAXIMIZED_BOTH);

        selectPanel = new SelectPanel();
        gradePanel = new GradePanel();
        resultPanel = new ResultPanel();

        //logic
        setFirstBtnListener();
        setSecondBtnListener();

        mainFrame.add(selectPanel.mainPanel);
        mainFrame.add(gradePanel.mainPanel);
        mainFrame.add(resultPanel.mainPanel);

        mainFrame.setVisible(true);
//        MainPanel m = new MainPanel();
    }

    private static void setFirstBtnListener() {
        for (int i=0; i<gradePanel.gradeTables.length; i++){
            gradePanel.gradeTables[i].selectToGradeBtns.addActionListener(new FirstBtnEventListener());
        }
    }

    private static void setSecondBtnListener() {
        resultPanel.gradeToResultBtn.addActionListener(new SecondBtnEventListener());
    }

    private static class FirstBtnEventListener implements ActionListener{
        int dest;
        ArrayList<Object> duplicated = new ArrayList<>();
        @Override
        public void actionPerformed(ActionEvent e) {
            //몇 학년의 버튼 이벤트가 발생했는지 확인
            for (int i=0; i<gradePanel.gradeTables.length; i++){
                if (e.getSource() == gradePanel.gradeTables[i].selectToGradeBtns){
                    dest = i;
                    break;
                }
            }

            //선택된 행이 없이 등록 버튼을 클릭할 경우 예외 호출
            try{
                if (selectPanel.mainTable.selectedRows==null){
                    throw new NoLectureSelectedException();
                }
            } catch (NoLectureSelectedException ex) {
                ex.viewDialog();
                return;
            }

            //하나의 선택된 행씩 Vector에 등록
            for (int i=0; i<selectPanel.mainTable.selectedRows.length; i++){
                Vector<Object> tmpData = makeVectorWithCombobox(); //성적 열 combobox
                for (int j=0; j<selectPanel.mainTable.table.getColumnCount(); j++){
                    Object item = selectPanel.mainTable.table.getValueAt(selectPanel.mainTable.selectedRows[i], j);
                    tmpData.add(item);
                }
                //이미 같은 과목이 들어와있는지 확인
                //나중에 선택된 창이 없다는 것과 함께 excpetion으로 빼기
                for (int r=0; r<4; r++){ //각 학년 table
                    for (int k=0; k<gradePanel.gradeTables[r].model.getRowCount(); k++){
                        if (tmpData.get(1) ==gradePanel.gradeTables[r].model.getValueAt(k, 1)){
                            duplicated.add(tmpData.get(1));
                            isSameLecture = true;
                            break;
                        }
                    }
                    if (isSameLecture) break;
                }
                if (isSameLecture){ //같은 과목이 있는 경우 등록하지 않고 다음 선택 행으로 이동
                    isSameLecture = false;
                } else{ //중복 없는 경우 저장된 vector를 grade table에 행 추가
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            gradePanel.gradeTables[dest].model.addRow(tmpData);
                        }
                    });
                }

                //중복 과목 안내
                if (!duplicated.isEmpty()){
                    //dialog 출력하는 로직
                    for (Object item:duplicated){
                        System.out.println(item+"이 중복되어 등록되지 않았습니다.");
                    }
                    duplicated.clear();
                }
            }
            selectPanel.mainTable.selectedRows = null; //선택 행 초기화
        }

        private Vector<Object> makeVectorWithCombobox() {
            Vector<Object> data = new Vector<>();
            data.add("Click");

            return data;
        }
    }

    private static class SecondBtnEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(resultPanel.paintThread(gradePanel.gradeTables)){
                    resultPanel.calcScoreAndTime(selectPanel.idComboBox.getSelectedItem().toString(), selectPanel.majorComboBox.getSelectedItem().toString());
                }
            } catch (NoScoreSelectedException ex) {
                ex.viewDialog();
            }
        }
    }



}
