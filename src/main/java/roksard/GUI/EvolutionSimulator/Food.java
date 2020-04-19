package roksard.GUI.EvolutionSimulator;

import roksard.GUI.EvolutionSimulator.gui.Circle;
import roksard.GUI.EvolutionSimulator.gui.Shape;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Food {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return exists == food.exists &&
                location.equals(food.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, exists);
    }

    public static final int ENERGY = 50;
    private Shape shape;
    private Point location;
    volatile boolean exists = true;
    Lock lock = new ReentrantLock();
    private int energy;

    public int getEnergy() {
        return energy;
    }

    public Food(Point location, int energy) {
        this.location = location;
        this.shape = new Circle(location, 5, Color.GREEN);
        this.energy = energy;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
        if (!exists) {
            shape = null;
        }
    }

    public Point getLocation() {
        return location;
    }

    public boolean isExists() {
        return exists;
    }

    public void draw(Graphics g) {
        if (shape != null) {
            shape.draw(g);
        }
    }
}
