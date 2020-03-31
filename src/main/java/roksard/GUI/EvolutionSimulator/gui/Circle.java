package roksard.GUI.EvolutionSimulator.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Shape {
    double size;
    Point location;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    Color color;

    public Circle(Point location, double size, Color color) {
        this.location = new Point(location);
        this.size = size;
        this.color = color;
    }

    public Circle(Point location) {
        this(location, 10, Color.GRAY);
    }

    public Circle(Point location, int size) {
        this(location, size, Color.GRAY);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(location.getX(), location.getY(), size, size);

        g2d.setColor(color);
        g2d.fill(circle);
    }

    public void setSize(double size) {
        if (size < 3) {
            this.size = 3;
        } else {
            this.size = size;
        }
    }
}
