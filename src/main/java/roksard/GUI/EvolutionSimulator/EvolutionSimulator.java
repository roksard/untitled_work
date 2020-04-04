package roksard.GUI.EvolutionSimulator;

import roksard.util.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class EvolutionSimulator {
    Logger log = Logger.getLogger("evo-sim");
    public final int tick = 1;
    AtomicInteger aliveCreatures = new AtomicInteger(0);
    volatile double creaturesSize = 0;
    List<Creature> creatures = new ArrayList<>();
    List<Food> foods = new ArrayList<>();
    Random rand = new Random();
    Point fieldSize = new Point(400, 400);
    ExecutorService executorService = Executors.newCachedThreadPool();

    public List<Runnable> shutdownNow() {
        return executorService.shutdownNow();
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Point randomPoint(int sizeX, int sizeY) {
        return new Point(rand.nextInt(sizeX), rand.nextInt(sizeY));
    }

    public void addCreature(Creature c) {
        synchronized (creatures) {
            creatures.add(c);
        }
        synchronized (this) {
            aliveCreatures.incrementAndGet();
            setCreaturesSize(getCreaturesSize() + Math.pow(c.getDna().size, 2));
        }
        executorService.execute(c);
    }

    public Food randomFood() {
        return new Food(randomPoint(400,400), Food.ENERGY);
    }

    public EvolutionSimulator(int creaturesNumber, int foodNumber) {
        for(int i = 0; i < foodNumber; i++) {
            foods.add(randomFood());
        }
        for(int i = 0; i < creaturesNumber; i++) {
            addCreature(new Creature(this, randomPoint(400, 400), new Dna(5, 10, 20)));
        }
        creatures.forEach(executorService::execute);
    }

    public Point getFieldSize() {
        return fieldSize;
    }

    public AtomicInteger getAliveCreatures() {
        return aliveCreatures;
    }

    public synchronized double getCreaturesSize() {
        return creaturesSize;
    }

    public synchronized void setCreaturesSize(double creaturesSize) {
        this.creaturesSize = creaturesSize;
    }

    public double getCreatureDensity() {
        return creaturesSize / (fieldSize.getX() * fieldSize.getY());
    }
}
