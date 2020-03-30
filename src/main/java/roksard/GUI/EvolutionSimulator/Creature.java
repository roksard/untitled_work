package roksard.GUI.EvolutionSimulator;

import roksard.GUI.EvolutionSimulator.gui.Circle;
import roksard.GUI.EvolutionSimulator.gui.Shape;
import roksard.util.GeometryUtils;

import java.awt.*;
import java.util.Optional;
import java.util.Random;

public class Creature implements Runnable {
    private Shape shape;
    private Point location;
    Random rand = new Random();
    int energy = 100;
    private Dna dna;
    private EvolutionSimulator simulator;
    State state = State.WANDER;
    static enum State {WANDER, GET_FOOD}
    Food foundFood;

    public Creature(EvolutionSimulator es, Point location, Dna dna) {
        this.simulator = es;
        this.location = new Point(location);
        this.dna = dna;
        this.shape = new Circle(location, dna.size, Color.RED);
    }

    private void updateShape() {
        shape.setLocation(location);
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }

    private Optional<Food> findClosestFood() {
        double min = -1;
        Food minF = null;
        for(Food food : simulator.getFoods()) {
            double dist = food.getLocation().distance(this.location);
            synchronized (food) {
                if (food.isExists() && (min == -1 || dist < min)) {
                    min = dist;
                    minF = food;
                }
            }
        }
        if (min > dna.senseRadius) {
            minF = null;
        }
        return Optional.ofNullable(minF);

    }

    private void walk() {
        double newX, newY;
        do {
            double x = location.getX();
            double y = location.getY();
            int deltaX = rand.nextInt(dna.speed * 2 + 1) - dna.speed;
            int deltaY = rand.nextInt(dna.speed * 2 + 1) - dna.speed;
            newX = x + deltaX;
            newY = y + deltaY;
        } while (newX < 0 || newX > simulator.getFieldSize().getX()
                || newY < 0 || newY > simulator.getFieldSize().getY());
        location.setLocation(newX, newY);
        updateShape();
    }

    private void walk(Point location) {
        if (this.location.distance(location) <= dna.speed) {
            this.location.setLocation(location);
        } else {
            this.location.setLocation(GeometryUtils.getPointOnVector(this.location, location, dna.speed));
        }
        updateShape();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(100);
                if (state == State.WANDER) {
                    walk();
                    Optional<Food> closestFood = findClosestFood();
                    closestFood.ifPresent(food -> {
                        state = State.GET_FOOD;
                        foundFood = food;
                    });
                } else if (state == State.GET_FOOD && foundFood != null) {
                    if (foundFood.getLocation().distance(this.location) < 2) {
                        //eat
                        foundFood.lock.lock();
                        try {
                            if (foundFood.isExists()) {
                                foundFood.setExists(false);
                                energy += foundFood.getEnergy();
                            }
                        } finally {
                            foundFood.lock.unlock();
                            foundFood = null;
                            state = State.WANDER;
                        }
                    } else {
                        //move towards food
                        walk(foundFood.getLocation());
                    }
                }
            }
        } catch (InterruptedException e) {

        }
    }
}
