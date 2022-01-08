import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel{
    JPanel centerPanel;
    JButton gradeToResultBtn;
    GridBagConstraints con = new GridBagConstraints();
    ImageIcon img1, img2, img3, img4;
    GradeThread gt1, gt2, gt3, gt4;

    public ResultPanel(){
        setLayout(new BorderLayout());
        centerPanel = new JPanel(new GridBagLayout());
        gradeToResultBtn = new JButton("▶");

        centerPanel.setBackground(Color.PINK);

        add(gradeToResultBtn, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void paintThread(GradeTablePanel[] tablePanels){
        gt1 = new GradeThread(10, 10, tablePanels[0].table, centerPanel);
        gt2 = new GradeThread(10, 60, tablePanels[1].table, centerPanel);
        gt3 = new GradeThread(10, 110, tablePanels[2].table, centerPanel);
        gt4 = new GradeThread(10, 160, tablePanels[3].table, centerPanel);

        gt1.start();
        gt2.start();
        gt3.start();
        gt4.start();
    }

    public void paintComponent(Graphics g){
        centerPanel.paint(g);

        img1 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\num1.png");
        img2 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\num2.png");
        img3 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\num3.png");
        img4 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\num4.png");

        g.drawString("어디갔어", 100, 100);

        if (gt1==null||gt2==null||gt3==null||gt4==null){
            g.drawImage(img1.getImage(), this.getWidth()/2, this.getHeight()/2, null);
            g.drawImage(img2.getImage(), this.getWidth()/2, this.getHeight()/2, null);
            g.drawImage(img3.getImage(), this.getWidth()/2, this.getHeight()/2, null);
            g.drawImage(img4.getImage(), this.getWidth()/2, this.getHeight()/2, null);
        } else{
            g.drawImage(img1.getImage(), gt1.x, gt1.y, null);
            g.drawImage(img2.getImage(), gt2.x, gt2.y, null);
            g.drawImage(img3.getImage(), gt3.x, gt3.y, null);
            g.drawImage(img4.getImage(), gt4.x, gt4.y, null);
        }
    }
}
