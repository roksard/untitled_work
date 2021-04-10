package roksard.fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class RxJPanel extends JPanel {
    {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 400));
    }
    public void drawTriangle(Graphics g, Point2D p0, Point2D p1, Point2D p2) {
        Graphics2D g2d = (Graphics2D)g;
        Line2D.Double line1 = new Line2D.Double(p0, p1);
        Line2D.Double line2 = new Line2D.Double(p1, p2);
        Line2D.Double line3 = new Line2D.Double(p2, p0);
        g2d.setColor(Color.GREEN);
        g2d.draw(line1);
        g2d.draw(line2);
        g2d.draw(line3);
    }

    public void drawLine(Graphics g, Point2D p0, Point2D p1) {
        Graphics2D g2d = (Graphics2D)g;
        Line2D.Double line1 = new Line2D.Double(p0, p1);
        g2d.setColor(Color.GREEN);
        g2d.draw(line1);
    }

}
