package roksard.GUI.EvolutionSimulator.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Circle implements Drawable {
    int x, y, size;
    Color color;

    public Circle(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public Circle(int x, int y) {
        this(x, y, 10, Color.GRAY);
    }

    public Circle(int x, int y, int size) {
        this(x, y, size, Color.GRAY);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);

        g2d.setColor(color);
        g2d.fill(circle);
    }

}
