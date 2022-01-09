import javax.swing.*;
import java.awt.*;

public class IconPanel extends JPanel {
    public IconPanel(){
        setLayout(new GridLayout(1, 3, 5, 5));
        ImageIcon img1 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\char1.gif");
        ImageIcon img2 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\char2.gif");
        ImageIcon img3 = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\char3.gif");

        JLabel l1 = new JLabel(img1);
        JLabel l2 = new JLabel(img2);
        JLabel l3 = new JLabel(img3);

        add(l1);
        add(l2);
        add(l3);
    }
}
