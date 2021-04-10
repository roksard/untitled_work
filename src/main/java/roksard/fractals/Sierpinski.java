package roksard.fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Sierpinski extends JPanel {
    {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 400));
    }
    private void drawTriangle(Graphics g, Point2D p0, Point2D p1, Point2D p2) {
        Graphics2D g2d = (Graphics2D)g;
        Line2D.Double line1 = new Line2D.Double(p0, p1);
        Line2D.Double line2 = new Line2D.Double(p1, p2);
        Line2D.Double line3 = new Line2D.Double(p2, p0);
        g2d.setColor(Color.GREEN);
        g2d.draw(line1);
        g2d.draw(line2);
        g2d.draw(line3);
    }


    private void sierpinski(Graphics g, Point2D p0, Point2D p1, Point2D p2, int limit) {
        if (limit <= 0) {
            drawTriangle(g, p0, p1, p2);
            return;
        }
        Point2D pA = new Point2D.Double(
                (p0.getX() + p1.getX()) / 2,
                (p0.getY() + p1.getY()) / 2
        );
        Point2D pB = new Point2D.Double(
                (p1.getX() + p2.getX()) / 2,
                (p1.getY() + p2.getY()) / 2
        );
        Point2D pC = new Point2D.Double(
                (p2.getX() + p0.getX()) / 2,
                (p2.getY() + p0.getY()) / 2
        );
        sierpinski(g, p0, pA, pC, limit - 1);
        sierpinski(g, pA, p1, pB, limit - 1);
        sierpinski(g, pC, pB, p2, limit - 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double width = g.getClip().getBounds().getWidth();
        double halfX = width / 2;
        double bottom = g.getClip().getBounds().getHeight() - 20;
        sierpinski(g,
                new Point2D.Double(halfX, 20),
                new Point2D.Double(20, bottom),
                new Point2D.Double(width - 20, bottom),
                6
        );
    }
}
