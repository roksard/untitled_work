package roksard.GUI.MovingCircles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Circle {
    private Random rand = new Random();
    int x, y, size;

    public Circle(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }

    public void moveRandomly() {
        x = x + rand.nextInt(7) - 3;
        y = y + rand.nextInt(7) - 3;
    }

}
