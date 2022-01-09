import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MainTablePanel extends MouseAdapter {
    private Vector<String> vector;
    private DefaultTableModel model;
    public JTable table;
    public JPanel mainPanel;
    private ReadCSV rcsv = new ReadCSV();
    private JScrollPane scroll;

    public int[] selectedRows;

    public MainTablePanel(){
        mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "교육과정표"));

        //열 이름 만들기
        vector = new Vector<String>();
        vector.addElement(rcsv.list.get(0).getName());
        vector.addElement(rcsv.list.get(0).getMandatory());
        vector.add(rcsv.list.get(0).getGrade());
        vector.add(rcsv.list.get(0).getTime());
        vector.add("p/f");

        //defaultTableModel 생성
        model = new DefaultTableModel(vector, 0){
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };
        table = new JTable(model);
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(450, 720));


        //데이터를 Vector 리스트 형식으로 추가
        for (int i=1; i<rcsv.list.size(); i++){
            Vector<String> v = new Vector<String>();
            v.add(rcsv.list.get(i).getName());
            v.add(rcsv.list.get(i).getMandatory());
            v.add(rcsv.list.get(i).getGrade());
            v.add(rcsv.list.get(i).getTime());
            v.add(rcsv.list.get(i).getPf());
            model.addRow(v);
        }

        setColumnWidth();
        mainPanel.add(scroll);
        table.addMouseListener(this);
    }

    private void setColumnWidth() {
        table.getColumnModel().getColumn(0).setPreferredWidth(270);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
    }


    @Override
    public void mouseClicked(MouseEvent e){
        if (e.getSource() == table){
            selectedRows = table.getSelectedRows();
        }
    }
}
