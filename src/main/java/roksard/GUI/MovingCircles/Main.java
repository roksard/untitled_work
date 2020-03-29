package roksard.GUI.MovingCircles;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Main extends JPanel{
    List<Circle> shapes = new ArrayList<>();

    public Main() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 400));
    }

    public void addCircle(Circle c) {
        synchronized (this) {
            shapes.add(c);
            repaint();
        }
    }


    double distance(Circle c, Point mpos) {
        final double cRad = c.size / 2d;
        final double distance = Point.distance(c.x + cRad, c.y + cRad, mpos.getX(), mpos.getY());
        return distance;
    }

    public void moveCircles() {
        synchronized (this) {
            shapes.forEach(shape -> {
                Point mpos = getMousePosition();
                final double cRad = shape.size / 2d;
                if (mpos != null && distance(shape, mpos) < cRad + 50) {
                    final double dist1 = distance(shape, mpos);
                    double dist2;
                    int origX = shape.x;
                    int origY = shape.y;
                    do {
                        shape.x = origX;
                        shape.y = origY;
                        shape.moveRandomly();
                        dist2 = distance(shape, mpos);
                    } while (dist2 < dist1);
                } else if (mpos != null && distance(shape, mpos) > cRad + 100) {
                    final double dist1 = distance(shape, mpos);
                    double dist2;
                    int origX = shape.x;
                    int origY = shape.y;
                    do {
                        shape.x = origX;
                        shape.y = origY;
                        shape.moveRandomly();
                        dist2 = distance(shape, mpos);
                    } while (dist2 > dist1);
                } else {
                    shape.moveRandomly();
                }
            });
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (this) {
            shapes.forEach(shape -> shape.draw(g));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Main mainc = new Main();
        frame.add(mainc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Random rand = new Random();
        Timer creator = new Timer();
        creator.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mainc.shapes.size() < 100) {
                    mainc.addCircle(new Circle(rand.nextInt(400), rand.nextInt(400), rand.nextInt(50) + 1));
                } else {
                    creator.cancel();
                }
            }
        }, 1000, 100);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mainc.moveCircles();
            }
        }, 100, 50);
    }
}
