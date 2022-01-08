import javax.swing.*;
import java.awt.*;

public class ResultPanel {
    JPanel mainPanel;
    JButton gradeToResultBtn;

    public ResultPanel(){
        mainPanel = new JPanel(new BorderLayout());
        gradeToResultBtn = new JButton("▶");

        mainPanel.add(gradeToResultBtn, BorderLayout.WEST);
    }
}
