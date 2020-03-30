package roksard.GUI.EvolutionSimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvolutionSimulator {
    List<Creature> creatures = new ArrayList<>();
    List<Food> foods = new ArrayList<>();
    Random rand = new Random();
    Point fieldSize = new Point(400, 400);
    ExecutorService executorService = Executors.newCachedThreadPool();

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
        executorService.execute(c);
    }

    public EvolutionSimulator(int creaturesNumber, int foodNumber) {
        final int foodEnergy = 50;
        for(int i = 0; i < foodNumber; i++) {
            foods.add(new Food(randomPoint(400,400), foodEnergy));
        }
        for(int i = 0; i < creaturesNumber; i++) {
            creatures.add(new Creature(this, randomPoint(400, 400), new Dna(5, 10, 200)));
        }
        creatures.forEach(executorService::execute);
    }

    public Point getFieldSize() {
        return fieldSize;
    }
}
