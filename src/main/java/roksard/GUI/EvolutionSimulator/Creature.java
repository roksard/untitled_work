package roksard.GUI.EvolutionSimulator;

import roksard.GUI.EvolutionSimulator.gui.Circle;
import roksard.GUI.EvolutionSimulator.gui.Drawable;

import java.awt.*;

public class Creature implements Runnable {
    Drawable shape;
    int x, y, size;
    int energy = 100;

    public Creature(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.shape = new Circle(x, y, size);
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {

        }
    }
}
