import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private static final int MAX_SCORE = 450;
    private static final int BORDER_GAP = 40;
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 10;
    private static final int Y_HATCH_CNT = 9;
    private ArrayList<Integer> scores;
    private ArrayList<Integer> times;
    private Graphics2D g2;
    private String[] legendString = {"성적", "이수학점"};

    public ChartPanel(ArrayList<Integer> scores, ArrayList<Integer> times){
        this.scores = scores;
        this.times = times;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        ArrayList<Point> graphScorePoints = new ArrayList<>();
        for (int i=0; i<scores.size(); i++){
            int x1 = (int)(i*xScale+BORDER_GAP);
            int y1 = (int)((MAX_SCORE-scores.get(i))*yScale+BORDER_GAP);
            graphScorePoints.add(new Point(x1, y1));
        }

        ArrayList<Point> graphTimePoints = new ArrayList<>();
        for (int i=0; i<times.size(); i++){
            int x1 = (int)(i*xScale+BORDER_GAP);
            int y1 = (int)((MAX_SCORE-times.get(i))*yScale+BORDER_GAP);
            graphTimePoints.add(new Point(x1, y1));
        }


        //x, y축 그리기
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight()-BORDER_GAP, getWidth()-BORDER_GAP, getHeight()-BORDER_GAP);
        g2.drawLine(getWidth()-BORDER_GAP, getHeight()-BORDER_GAP, getWidth()-BORDER_GAP, BORDER_GAP);

        //y축 칸 구분 그리기
        for (int i=0; i<Y_HATCH_CNT; i++){
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight()-(((i+1)*(getHeight()-BORDER_GAP*2))/Y_HATCH_CNT+BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
            double tmp = (double)(50*(i+1))/100;
            g2.drawString(Double.toString(tmp), x0-(BORDER_GAP/2), y0);
        }
        for (int i=0; i<Y_HATCH_CNT; i++){
            int x0 = getWidth()-BORDER_GAP;
            int x1 = getWidth()-BORDER_GAP - GRAPH_POINT_WIDTH;
            int y0 = getHeight()-(((i+1)*(getHeight()-BORDER_GAP*2))/Y_HATCH_CNT+BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
            g2.drawString(String.valueOf((i+1)*3), x0, y0);
        }

        //x축 칸 구분 그리기
        g2.drawString("1학년", (float) (graphScorePoints.get(0).x), getHeight()-BORDER_GAP+(GRAPH_POINT_WIDTH*1.5f));
        for (int i=0; i<graphScorePoints.size()-1; i++){
            int x0 = graphScorePoints.get(i+1).x;
            int x1 = x0;
            int y0 = getHeight()-BORDER_GAP;
            int y1 = y0-GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
            g2.drawString((i+2)+"학년", x0-(GRAPH_POINT_WIDTH*1.5f), y0+(GRAPH_POINT_WIDTH*1.5f));
        }

        drawLineAndOval(graphScorePoints, Color.BLUE, Color.CYAN );
        drawLineAndOval(graphTimePoints, Color.DARK_GRAY, Color.PINK);

        //범례 표시
        int x = BORDER_GAP;
        int y = BORDER_GAP/6;
        g2.setColor(Color.CYAN);
        g2.fillRect(x, y, BORDER_GAP/2, BORDER_GAP/4);
        g2.setColor(Color.PINK);
        g2.fillRect(x, y+(BORDER_GAP/4)+GRAPH_POINT_WIDTH/2, BORDER_GAP/2, BORDER_GAP/4);
        g2.setColor(Color.BLACK);
        g2.drawString(legendString[0], x+(BORDER_GAP/2)+GRAPH_POINT_WIDTH, y+GRAPH_POINT_WIDTH);
        g2.drawString(legendString[1], x+(BORDER_GAP/2)+GRAPH_POINT_WIDTH, y+(BORDER_GAP/4)+GRAPH_POINT_WIDTH+GRAPH_POINT_WIDTH/2);

    }

    private void drawLineAndOval(ArrayList<Point> points, Color line, Color oval) {
        //직선 그리기
        Stroke oldStroke = g2.getStroke();
        g2.setColor(line);
        g2.setStroke(GRAPH_STROKE);
        for (int i=0; i<points.size()-1; i++){
            int x0 = points.get(i).x;
            int y0 = points.get(i).y;
            int x1 = points.get(i+1).x;
            int y1 = points.get(i+1).y;
            g2.drawLine(x0, y0, x1, y1);
        }

        //점 그리기
        g2.setStroke(oldStroke);
        g2.setColor(oval);
        for (int i=0; i<points.size(); i++){
            int x = points.get(i).x - GRAPH_POINT_WIDTH/2;
            int y = points.get(i).y - GRAPH_POINT_WIDTH/2;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

}
