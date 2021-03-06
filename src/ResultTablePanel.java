import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ResultTablePanel extends JPanel{
    String[] columnName = {"구분", "졸업학점", "이수학점", "부족학점"};
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    public ResultTablePanel(){
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        table.setRowHeight(40);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 100));
        renderer.setHorizontalAlignment(JLabel.CENTER);

        setMajorClassfier();
        setAllColumnsAlignCenter();
        add(scrollPane);
    }

    private void setAllColumnsAlignCenter() {
        for (int i=0; i<table.getColumnCount(); i++){
            table.getColumn(columnName[i]).setCellRenderer(renderer);
        }
    }

    private void setMajorClassfier() {
        model.addRow(new Vector<>());
        model.addRow(new Vector<>());
        
        table.setValueAt("전공필수", 0, 0);
        table.setValueAt("총 이수학점", 1, 0);
    }

    public void setTimesToTable(int gradRequiredTime, int gradTotalTime, int stdRequiredTime, int stdTotalTime){
        table.setValueAt(gradRequiredTime, 0, 1);
        table.setValueAt(gradTotalTime, 1, 1);
        table.setValueAt(stdRequiredTime, 0, 2);
        table.setValueAt(stdTotalTime, 1, 2);
        table.setValueAt(Math.max(gradRequiredTime - stdRequiredTime, 0), 0, 3);
        table.setValueAt(Math.max(gradTotalTime - stdTotalTime, 0), 1, 3);
    }
}
