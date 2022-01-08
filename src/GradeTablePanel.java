import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class GradeTablePanel{
    Object[][] data ={};
    String[] columnName = {"과목명(국문)", "인정교과구분", "이수단계", "학점/이론/실습", "p/f"};
    JPanel mainPanel, westPanel;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    JButton selectToGradeBtns;

    public GradeTablePanel(){
        mainPanel = new JPanel(new BorderLayout(10, 0));
        westPanel = new JPanel(new GridLayout(4, 1, 150, 150));
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        selectToGradeBtns = new JButton("▶");
        westPanel.add(selectToGradeBtns);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);
    }

}
