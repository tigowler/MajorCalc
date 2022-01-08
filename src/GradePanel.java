import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
            gradeTables[i].mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), (i+1)+"학년 이수 강의"));
            gradePanels[i].add(gradeTables[i].mainPanel);
            mainPanel.add(gradePanels[i]);
        }

    }

}
