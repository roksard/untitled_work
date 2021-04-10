package roksard.fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class RxJPanel extends JPanel {
    {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 400));
    }
    Color drawColor = Color.BLACK;
    public void drawTriangle(Graphics g, Point2D p0, Point2D p1, Point2D p2) {
        Graphics2D g2d = (Graphics2D)g;
        Line2D.Double line1 = new Line2D.Double(p0, p1);
        Line2D.Double line2 = new Line2D.Double(p1, p2);
        Line2D.Double line3 = new Line2D.Double(p2, p0);
        g2d.setColor(drawColor);
        g2d.draw(line1);
        g2d.draw(line2);
        g2d.draw(line3);
    }

    public boolean pointsAnyVisible(Graphics g, Point2D... points) {
        for (Point2D p : points) {
            boolean visX = p.getX() >= 0 && p.getX() <= g.getClip().getBounds2D().getWidth();
            boolean visY = p.getY() >= 0 && p.getY() <= g.getClip().getBounds2D().getHeight();
            if (visX || visY) {
                return true;
            }
        }
        return false;
    }

    public double distance(Point2D p0, Point2D p1) {
        double dx = Math.abs(p1.getX() - p0.getX());
        double dy = Math.abs(p1.getY() - p0.getY());
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void drawLine(Graphics g, Point2D p0, Point2D p1) {
        Graphics2D g2d = (Graphics2D)g;
        Line2D.Double line1 = new Line2D.Double(p0, p1);
        g2d.setColor(drawColor);
        g2d.draw(line1);
    }

}
