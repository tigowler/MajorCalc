import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private static final int MAX_SCORE = 450;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.CYAN;
    private static final Color GRAPH_POINT_COLOR = Color.PINK;
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 9;
    private ArrayList<Integer> scores = new ArrayList<>();

    public ChartPanel(ArrayList<Integer> scores){
        this.scores = scores;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        ArrayList<Point> graphPoints = new ArrayList<>();
        for (int i=0; i<scores.size(); i++){
            int x1 = (int)(i*xScale+BORDER_GAP);
            int y1 = (int)((MAX_SCORE-scores.get(i))*yScale+BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        //x, y축 그리기
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, getWidth()-BORDER_GAP, getHeight()-BORDER_GAP);

        //y축 칸 구분 그리기
        for (int i=0; i<Y_HATCH_CNT; i++){
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight()-(((i+1)*(getHeight()-BORDER_GAP*2))/Y_HATCH_CNT+BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i=0; i<graphPoints.size()-1; i++){
            int x0 = graphPoints.get(i+1).x;
            int x1 = graphPoints.get(i+1).x;
            int y0 = getHeight()-BORDER_GAP;
            int y1 = y0-GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i=0; i<graphPoints.size()-1; i++){
            int x0 = graphPoints.get(i).x;
            int y0 = graphPoints.get(i).y;
            int x1 = graphPoints.get(i+1).x;
            int y1 = graphPoints.get(i+1).y;
            g2.drawLine(x0, y0, x1, y1);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i=0; i<graphPoints.size(); i++){
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH/2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH/2;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }

    }

}
