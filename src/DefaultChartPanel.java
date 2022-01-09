import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefaultChartPanel extends JPanel {
    private static final int BORDER_GAP = 40;

    public DefaultChartPanel(){

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //x, y축 그리기
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, getWidth()-BORDER_GAP, getHeight()-BORDER_GAP);
        g2.drawLine(getWidth()-BORDER_GAP, getHeight()-BORDER_GAP, getWidth()-BORDER_GAP, BORDER_GAP);

    }
}
