import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class JTableExMain extends MouseAdapter implements ActionListener {
    private Vector<String> vector;
    private DefaultTableModel model;
    private JTable table;
    private JButton addBtn, delBtn;
    private JFrame jf;
    private ReadCSV rcsv = new ReadCSV();

    private int selectedRow=-1;

    public JTableExMain(){
        jf = new JFrame("JTable Test: CHO");

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
        JScrollPane scroll = new JScrollPane(table);

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

        //container에 추가
        jf.add(scroll, "Center");
        jf.add(p, "South");
        jf.setBounds(700, 100, 500, 400);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addBtn.addActionListener(this);
        delBtn.addActionListener(this);
        table.addMouseListener(this);
    }

    private void delete(){
        model.removeRow(selectedRow);
//        selectedRow = -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==addBtn){
//            insert();
        } else if (e.getSource()==delBtn){
            delete();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if (e.getSource() == table){
            selectedRow = table.getSelectedRow();
            int[] select = table.getSelectedRows();
            for (int sel: select){
                System.out.println(sel);
            }
//            System.out.println("Selected! = "+selectedRow);
        }
    }
}
