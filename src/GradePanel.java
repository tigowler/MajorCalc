import javax.swing.*;
import java.awt.*;

public class GradePanel{
    JPanel mainPanel;
    GradeTablePanel[] gradeTables = new GradeTablePanel[4];
    JPanel[] gradePanels = new JPanel[4];

    public GradePanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        for (int i=0; i<4; i++){
            gradeTables[i] = new GradeTablePanel();
            gradePanels[i] = new JPanel();
            gradePanels[i].add(new JLabel((i+1)+"학년 이수 강의"));
            gradePanels[i].add(gradeTables[i].mainPanel);
            mainPanel.add(gradePanels[i]);
        }

    }

}
