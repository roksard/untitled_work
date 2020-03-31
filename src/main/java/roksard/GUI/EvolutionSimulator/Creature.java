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
    double energy = 100;
    private Dna dna;
    private EvolutionSimulator simulator;
    State state = State.WANDER;
    static enum State {WANDER, GET_FOOD}
    Food foundFood;
    boolean isAlive = true;

    public Creature(EvolutionSimulator es, Point location, Dna dna) {
        this.simulator = es;
        this.location = new Point(location);
        this.dna = dna;
        this.shape = new Circle(location, dna.size, Color.RED);
    }

    private void updateShape() {
        if (!isAlive) {
            shape = new Circle(this.location, this.dna.size, Color.GRAY);
        } else {
            shape.setSize(this.dna.size);
            shape.setLocation(location);
        }
    }

    public void draw(Graphics g) {
        if (shape != null) {
            shape.draw(g);
        }
    }

    private Optional<Food> findClosestFood() {
        double min = -1;
        Food minF = null;
        synchronized (simulator.getFoods()) {
            for (Food food : simulator.getFoods()) {
                double dist = food.getLocation().distance(this.location);
                synchronized (food) {
                    if (food.isExists() && (min == -1 || dist < min)) {
                        min = dist;
                        minF = food;
                    }
                }
            }
        }
        if (min > dna.senseRadius) {
            minF = null;
        }
        return Optional.ofNullable(minF);

    }

    private double rangeLimit(double from, double to, double value) {
        double result = value;
        if (result < from)
            result = from;
        else if(result > to)
            result = to;
        return result;
    }

    private void walk() {
        double newX, newY;
        double deltaX = rand.nextInt((int)Math.round(dna.speed * 2 + 1)) - dna.speed;
        double deltaY = rand.nextInt((int)Math.round(dna.speed * 2 + 1)) - dna.speed;
        newX = rangeLimit(0, simulator.getFieldSize().getX(), location.getX() + deltaX);
        newY = rangeLimit(0, simulator.getFieldSize().getY(), location.getY() + deltaY);
        location.setLocation(newX, newY);
        addEnergy(walkEnergy());
        updateShape();
    }

    private void walk(Point location) {
        if (this.location.distance(location) <= dna.speed) {
            this.location.setLocation(location);
        } else {
            this.location.setLocation(GeometryUtils.getPointOnVector(this.location, location, dna.speed));
        }
        addEnergy(walkEnergy());
        updateShape();
    }

    private double walkEnergy() {
        return -this.dna.size * this.dna.speed * this.dna.speed * 0.01;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void addEnergy(double deltaEnergy) {
        this.energy += deltaEnergy;
        if (this.energy <= 0) {
            synchronized (simulator.getCreatures()) {
                synchronized (this) {
                    this.isAlive = false;
                }
            }
            updateShape();
        } else if (this.energy > 200) {
            Dna newDna = new Dna(this.dna);
            final double mutationMultiplier = 0.5; //how strong a mutation is against current value
            final int mutationProbability = 50; //in % approx
            int mutation = rand.nextInt(100 / mutationProbability + 3);

            if (mutation == 1) {
                newDna.speed = rangeLimit(1, 50, newDna.speed + (rand.nextDouble()-0.5) * newDna.speed * mutationMultiplier);
            }
            if (mutation == 2) {
                newDna.senseRadius = rangeLimit(0, 1000, newDna.senseRadius + (rand.nextDouble()-0.5) * newDna.senseRadius * mutationMultiplier);
            }
            if (mutation == 3) {
                newDna.size = rangeLimit(1, 30, newDna.size + (rand.nextDouble()-0.5) * newDna.size * mutationMultiplier);
            }
            simulator.addCreature(new Creature(simulator, this.location, newDna));
            addEnergy(-100);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted() && isAlive) {
                Thread.sleep(2);
                if (state == State.WANDER) {
                    walk();
                    Optional<Food> closestFood = findClosestFood();
                    closestFood.ifPresent(food -> {
                        state = State.GET_FOOD;
                        foundFood = food;
                    });
                } else if (state == State.GET_FOOD && foundFood != null) {
                    boolean foodExists;
                    synchronized (foundFood) {
                        foodExists = foundFood.isExists();
                    }
                    if (!foodExists) {
                        foundFood = null;
                        state = State.WANDER;
                    } else {
                        if (foundFood.getLocation().distance(this.location) < 1) {
                            //eat
                            foundFood.lock.lock();
                            try {
                                if (foundFood.isExists()) {
                                    foundFood.setExists(false);
                                    addEnergy(foundFood.getEnergy());
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
            }
        } catch (Throwable e) {

        }
    }
}
