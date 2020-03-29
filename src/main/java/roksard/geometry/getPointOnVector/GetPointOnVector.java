package roksard.geometry.getPointOnVector;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GetPointOnVector {
    public static Point getPointOnVector(Point vectorBegin, Point vectorEnd, double desiredDistFromVectorBegin) {
        double x1 = vectorBegin.getX();
        double y1 = vectorBegin.getY();
        double x2 = vectorEnd.getX();
        double y2 = vectorEnd.getY();
        double deltaY = y2-y1;
        double deltaX = x2-x1;
        double alpha = Math.atan(deltaY / deltaX);
        double newX = x1 + desiredDistFromVectorBegin * Math.sin(Math.PI/2 - alpha);
        double newY = y1 + desiredDistFromVectorBegin * Math.sin(alpha);
        return new Point((int)Math.round(newX), (int)Math.round(newY));
    }

    public static void main(String[] args) throws InterruptedException {
        int x1 = 50;
        int y1 = 50;
        int x2 = 200;
        int y2 = 70;
        Point np = getPointOnVector(new Point(x1, y1), new Point(x2, y2), 2);
        Point np2 = getPointOnVector(new Point(x1, y1), new Point(x2, y2), 50);
        Point np3 = getPointOnVector(new Point(x1, y1), new Point(x2, y2), -40);
        Point np4 = getPointOnVector(new Point(x1, y1), new Point(x2, y2), 200);

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
                drawCircle(g, x1, y1, 2, Color.CYAN);
                drawCircle(g, x2, y2, 2, Color.CYAN);
                drawCircle(g, np.getX(), np.getY(), 4, Color.CYAN);
                drawCircle(g, np2.getX(), np2.getY(), 4, Color.RED);
                drawCircle(g, np3.getX(), np3.getY(), 4, Color.YELLOW);
                drawCircle(g, np4.getX(), np4.getY(), 4, Color.GREEN);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }
}
