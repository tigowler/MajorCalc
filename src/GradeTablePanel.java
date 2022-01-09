import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GradeTablePanel extends MouseAdapter implements ActionListener {
    Object[][] data ={};
    String[] columnName = {"성적", "과목명(국문)", "인정교과구분", "이수단계", "학점/이론/실습", "p/f"};
    JPanel mainPanel, westPanel, tablePanel;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    JButton selectToGradeBtns, deleteBtn;

    int[] selectedRows;

    public GradeTablePanel(){
        tablePanel = new JPanel();
        mainPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new GridLayout(4, 1));
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        setUpScoreColumn(table.getColumnModel().getColumn(0));
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 150));
        selectToGradeBtns = new JButton("▶");
        deleteBtn = new JButton("❌");

        setColumnWidth();

        tablePanel.add(scrollPane);
        westPanel.add(selectToGradeBtns);
        westPanel.add(deleteBtn);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);

        table.addMouseListener(this);
        deleteBtn.addActionListener(this);
    }

    private void setColumnWidth() {
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
    }

    private void setUpScoreColumn(TableColumn column) {
        String[] item = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "P", "F"};
        JComboBox<String> comboBox = new JComboBox<>(item);
        column.setCellEditor(new DefaultCellEditor(comboBox));
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if (e.getSource() == table){
            selectedRows = table.getSelectedRows();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            if (selectedRows==null){
                throw new NoLectureSelectedException();
            }
            for (int i=selectedRows.length-1; i>=0; i--){
                model.removeRow(selectedRows[i]);
            }
            selectedRows=null;
        } catch (NoLectureSelectedException ex) {
            ex.viewDialog();
        }

    }
}
