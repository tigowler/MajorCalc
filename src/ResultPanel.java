import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel{
    JPanel centerPanel, threadPanel;
    JButton gradeToResultBtn;
    GridBagConstraints con = new GridBagConstraints();
    GradeThread gt1, gt2, gt3, gt4;
    JLabel[] grades = {new JLabel("1학년"), new JLabel("2학년"), new JLabel("3학년"), new JLabel("4학년")};
    JLabel l1, l2, l3, l4;

    public ResultPanel(){
        setLayout(new BorderLayout());
        centerPanel = new JPanel(new GridBagLayout());
        threadPanel = new JPanel(new GridBagLayout());
        l1 = new JLabel("0%");
        l2 = new JLabel("0%");
        l3 = new JLabel("0%");
        l4 = new JLabel("0%");
        setLabelAlignment();
        setThreadPanel();
        gradeToResultBtn = new JButton("▶");
        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(threadPanel, con);
        add(gradeToResultBtn, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void setLabelAlignment() {
        for (JLabel l: grades){
            l.setHorizontalAlignment(JLabel.CENTER);
        }
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);
        l4.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setThreadPanel() {
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.weighty = 1;
        con.gridy = 0;
        for (int i=0; i<grades.length; i++){
            con.gridx = i;
            threadPanel.add(grades[i], con);
        }
        con.gridy = 1;
        con.gridx = 0;
        threadPanel.add(l1, con);
        con.gridx = 1;
        threadPanel.add(l2, con);
        con.gridx = 2;
        threadPanel.add(l3, con);
        con.gridx = 3;
        threadPanel.add(l4, con);
    }

    public void paintThread(GradeTablePanel[] tablePanels){
        gt1 = new GradeThread(tablePanels[0].table, l1, threadPanel);
        gt2 = new GradeThread(tablePanels[1].table, l2, threadPanel);
        gt3 = new GradeThread(tablePanels[2].table, l3, threadPanel);
        gt4 = new GradeThread(tablePanels[3].table, l4, threadPanel);

        gt1.start();
        gt2.start();
        gt3.start();
        gt4.start();
    }
}
