import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GradeTablePanel{
    Object[][] data ={{"객체", "전필", "2학", "3/3/0", false}, };
    String[] columnName = {"과목명(국문)", "인정교과구분", "이수단계", "학점/이론/실습", "p/f"};
    JPanel mainPanel, westPanel;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    JButton[] selectToGradeBtns = new JButton[4];

    public GradeTablePanel(){
        mainPanel = new JPanel(new BorderLayout(10, 0));
        westPanel = new JPanel(new GridLayout(4, 1, 150, 150));
        model = new DefaultTableModel(data, columnName);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        setSelectToGradePanel();
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);
    }

    private void setSelectToGradePanel() {
        for (int i=0; i<selectToGradeBtns.length; i++){
            selectToGradeBtns[i] = new JButton("▶");
            selectToGradeBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    addRowToGradeTable();
                }
            });
            westPanel.add(selectToGradeBtns[i]);
        }
    }

}
