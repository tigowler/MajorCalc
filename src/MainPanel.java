import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainPanel{
    SelectPanel selectPanel;
    GradePanel gradePanel;
    ResultPanel resultPanel;

    public MainPanel(){

        selectPanel = new SelectPanel();
        gradePanel = new GradePanel();
        resultPanel = new ResultPanel();


    }

    public void addRowToGradeTable(){
        int[] rows = selectPanel.mainTable.selectedRows;
        for (int row : rows){
            System.out.print(row+" ");
        }
        System.out.println();
    }


//    private class FirstButtonListener implements ActionListener {
//        int dest;
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            for (int i=0; i<selectToGradeBtns.length; i++){
//                if (e.getSource()==selectToGradeBtns[i]){
//                    dest = i;
//                    break;
//                }
//            }
//
//            MainTablePanel tablePanel = selectPanel.mainTable;
//            if (tablePanel.selectedRows.length<=0){
//                System.out.println("none-of rows selected");
//                return;
//            }
//            for (int i=0; i<tablePanel.selectedRows.length; i++){
//                for (int j=0; j<tablePanel.table.getColumnCount(); j++){
//                    Object item = tablePanel.table.getValueAt(tablePanel.selectedRows[i], j);
//                    if (item == null){
//                        tmpV.add(false);
//                    } else if (item == "✔"){
//                        tmpV.add(true);
//                    } else{
//                        tmpV.add(item);
//                    }
//                }
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        gradePanel.gradeTables[dest].addRowToGradeTable(tmpV);
//                    }
//                });
//                tmpV.clear();
//
//            }
//
//        }
//    }
//
//    private class SecondButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("clicked!");
//        }
//    }
}
