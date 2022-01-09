import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ResultScoreTablePanel extends JPanel {
    String[] columnName = {"구분", "1학년", "2학년", "3학년", "4학년"};
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    public ResultScoreTablePanel(){
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        table.setRowHeight(40);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 100));

        setClassifier();
        add(scrollPane);
    }

    private void setClassifier() {
        model.addRow(new Vector<>());
        model.addRow(new Vector<>());

        table.setValueAt("성적", 0, 0);
        table.setValueAt("총 이수학점", 1, 0);
    }

    public void setScoreToTable(ArrayList<String> scores, ArrayList<Integer> totalTime){
        for (int i=0; i<scores.size(); i++){
            table.setValueAt(scores.get(i), 0, (i+1));
        }
        for (int i=0; i<totalTime.size(); i++){
            table.setValueAt(totalTime.get(i), 1, (i+1));
        }
    }


}
