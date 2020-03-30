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

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Point randomPoint(int sizeX, int sizeY) {
        return new Point(rand.nextInt(sizeX), rand.nextInt(sizeY));
    }

    public EvolutionSimulator(int creaturesNumber, int foodNumber) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final int foodEnergy = 50;
        for(int i = 0; i < foodNumber; i++) {
            foods.add(new Food(randomPoint(400,400), foodEnergy));
        }
        for(int i = 0; i < creaturesNumber; i++) {
            creatures.add(new Creature(this, randomPoint(400, 400), new Dna(5, 10, 50)));
        }
        creatures.forEach(exec::execute);
    }

    public Point getFieldSize() {
        return fieldSize;
    }
}
