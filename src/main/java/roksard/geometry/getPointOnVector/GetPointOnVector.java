package roksard.geometry.getPointOnVector;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GetPointOnVector {
    static Random rand = new Random();
    public static Point getPointOnVector(Point vectorBegin, Point vectorEnd, double desiredDistFromVectorBegin) {
        double x1 = vectorBegin.getX();
        double y1 = vectorBegin.getY();
        double x2 = vectorEnd.getX();
        double y2 = vectorEnd.getY();
        double deltaY = y2-y1;
        double deltaX = x2-x1;
        double signX = deltaX < 0 ? -1 : 1;
        double signY = deltaY < 0 ? -1 : 1;
        double alpha = Math.atan(deltaY / deltaX);
        double newX = x1 + desiredDistFromVectorBegin * Math.abs(Math.sin(Math.PI/2 - alpha)) * signX;
        double newY = y1 + desiredDistFromVectorBegin * Math.abs(Math.sin(alpha))  * signY;
        return new Point((int)Math.round(newX), (int)Math.round(newY));
    }

    public static void main(String[] args) throws InterruptedException {
        Point begin = new Point(294, 237);
        Point end = new Point(177, 198);
        Point np = getPointOnVector(begin, end, begin.distance(end) / 2);

        JFrame frame = new JFrame();
        frame.add(new JPanel() {
            double coordinateScaleForDrawing = 1;
            {
                setBackground(Color.BLACK);
                setPreferredSize(new Dimension(400, 400));
            }
            private void drawCircle(Graphics g, double x, double y, double size, Color color) {
                double s = coordinateScaleForDrawing;
                Graphics2D g2d = (Graphics2D)g;
                Ellipse2D.Double circle = new Ellipse2D.Double(x * s, y * s, size, size);
                g2d.setColor(color);
                g2d.fill(circle);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCircle(g, begin.getX(), begin.getY(), 4, Color.GREEN);
                drawCircle(g, end.getX(), end.getY(), 4, Color.RED);
                drawCircle(g, np.getX(), np.getY(), 4, Color.BLUE);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                begin.setLocation(rand.nextInt(300)+10, rand.nextInt(300)+10);
                end.setLocation(rand.nextInt(300)+10, rand.nextInt(300)+10);
                np.setLocation(getPointOnVector(begin, end, begin.distance(end) *  9/10));
                frame.repaint();
            }
        }, 2000, 2000);
    }
}
