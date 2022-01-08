import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TestMain {
    static SelectPanel selectPanel;
    static GradePanel gradePanel;
    static ResultPanel resultPanel;
    static boolean isSameLecture = false;

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("SM IT MAJOR CALC");
        mainFrame.setLayout(new GridLayout(1, 3));
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setExtendedState(MAXIMIZED_BOTH);

        selectPanel = new SelectPanel();
        gradePanel = new GradePanel();
        resultPanel = new ResultPanel();

        //logic
        setFirstBtnListener();

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

    private static class FirstBtnEventListener implements ActionListener{
        int dest;
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i<gradePanel.gradeTables.length; i++){
                if (e.getSource() == gradePanel.gradeTables[i].selectToGradeBtns){
                    dest = i;
                    System.out.println(dest);
                    break;
                }
            }

            for (int i=0; i<selectPanel.mainTable.selectedRows.length; i++){
                Vector<Object> tmpData = makeVectorWithCombobox();
                for (int j=0; j<selectPanel.mainTable.table.getColumnCount(); j++){
                    Object item = selectPanel.mainTable.table.getValueAt(selectPanel.mainTable.selectedRows[i], j);
                    tmpData.add(item);
                }
                //이미 같은 과목이 들어와있는지 확인
                //나중에 선택된 창이 없다는 것과 함께 excpetion으로 빼기
                for (int r=0; r<4; r++){
                    for (int k=0; k<gradePanel.gradeTables[r].model.getRowCount(); k++){
                        if (tmpData.get(1) ==gradePanel.gradeTables[r].model.getValueAt(k, 1)){
                            isSameLecture = true;
                            break;
                        }
                    }
                    if (isSameLecture) break;
                }

                if (isSameLecture){
                    isSameLecture = false;
                    System.out.println("이미 등록된 과목입니다.");
                    continue;
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gradePanel.gradeTables[dest].model.addRow(tmpData);
                    }
                });
            }
        }

        private Vector<Object> makeVectorWithCombobox() {
            Vector<Object> data = new Vector<>();
            data.add("Click");

            return data;
        }
    }



}
