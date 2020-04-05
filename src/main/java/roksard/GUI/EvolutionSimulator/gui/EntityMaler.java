package roksard.GUI.EvolutionSimulator.gui;

import roksard.GUI.EvolutionSimulator.Creature;
import roksard.GUI.EvolutionSimulator.EvolutionSimulator;

import java.awt.*;
import java.util.Comparator;

public class EntityMaler {
    EvolutionSimulator simulator;
    public EntityMaler(EvolutionSimulator simulator) {
        this.simulator = simulator;
    }

    public void draw(Graphics g) {
        synchronized (simulator.getFoods()) {
            simulator.getFoods().forEach(food -> food.draw(g));
        }
        synchronized (simulator.getCreatures()) {
            simulator.getCreatures().sort(new Comparator<Creature>() {
                @Override
                public int compare(Creature o1, Creature o2) {
                    boolean alive1;
                    boolean alive2;
                    synchronized (o1) {
                        alive1 = o1.isAlive();
                    }
                    synchronized (o2) {
                        alive2 = o2.isAlive();
                    }
                    return Boolean.compare(alive1, alive2);
                }
            });
            simulator.getCreatures().forEach(creature -> creature.draw(g));
        }
    }
}
