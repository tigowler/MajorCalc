import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MainTablePanel extends MouseAdapter implements ActionListener {
    private Vector<String> vector;
    private DefaultTableModel model;
    public JTable table;
    private JButton addBtn, delBtn;
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
        scroll.setPreferredSize(new Dimension(420, 720));

        //버튼 추가
        addBtn = new JButton("ADD");
        delBtn = new JButton("DELETE");

        //패널에 버튼 추가
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.add(addBtn);
        p.add(delBtn);

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

        //container에 추가
//        mainPanel.add(scroll, "Center");
//        mainPanel.add(p, "South");
        mainPanel.add(scroll);
//        mainPanel.setBounds(700, 100, 500, 400);

        addBtn.addActionListener(this);
        delBtn.addActionListener(this);
        table.addMouseListener(this);
    }

    private void setColumnWidth() {
        table.getColumnModel().getColumn(0).setPreferredWidth(270);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
    }

//    private void delete(){
//        model.removeRow(selectedRow);
////        selectedRow = -1;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==addBtn){
//            insert();
        } else if (e.getSource()==delBtn){
//            delete();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if (e.getSource() == table){
            selectedRows = table.getSelectedRows();
        }
//        for (int row : selectedRows){
//            System.out.print(row+" ");
//        }
//        System.out.println();
    }
}
