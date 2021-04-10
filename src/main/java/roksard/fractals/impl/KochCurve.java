package roksard.fractals.impl;

import roksard.fractals.RxJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class KochCurve extends RxJPanel {
    private Point2D p0, p1;
    private double mult;

    public KochCurve(double mult) {
        this.mult = mult;
    }

    public void koch(Graphics g, Point2D p0, Point2D p1, int limit) {
        if (!pointsAnyVisible(g, p0, p1)) {
            return;
        }
        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        double unit = dist / 3;
        double angle = Math.atan2(dy, dx);
        Point2D pA = new Point2D.Double(
                p0.getX() + dx / 3,
                p0.getY() + dy / 3
        );
        Point2D pC = new Point2D.Double(
                p1.getX() - dx / 3,
                p1.getY() - dy / 3
        );
        Point2D pB = new Point2D.Double(
                pA.getX() + Math.cos(angle - Math.PI / 3) * unit,
                pA.getY() + Math.sin(angle - Math.PI / 3) * unit
        );
        if (limit > 0 && distance(p0, p1) >= 1) {
            koch(g, p0, pA, limit - 1);
            koch(g, pA, pB, limit - 1);
            koch(g, pB, pC, limit - 1);
            koch(g, pC, p1, limit - 1);
        } else {
            drawLine(g, p0, pA);
            drawLine(g, pA, pB);
            drawLine(g, pB, pC);
            drawLine(g, pC, p1);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (p0 == null || p1 == null) {
            double width = g.getClip().getBounds().getWidth();
            double halfY = g.getClip().getBounds().getHeight() / 2;
            p0 = new Point2D.Double(10, halfY);
            p1 = new Point2D.Double(width - 10, halfY);
        } else {
            p1.setLocation(p1.getX() * mult, p1.getY() * mult);
        }
        koch(g,
                p0,
                p1,
                10
        );
    }
}
