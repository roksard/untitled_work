package roksard.GUI.EvolutionSimulator.gui;

import roksard.GUI.EvolutionSimulator.Creature;
import roksard.GUI.EvolutionSimulator.EvolutionSimulator;

import java.awt.*;
import java.util.Comparator;

public class EntityMalerNoSort extends EntityMaler {
    public EntityMalerNoSort(EvolutionSimulator simulator) {
        super(simulator);
    }

    @Override
    public void draw(Graphics g) {
        synchronized (simulator.getDeadCreatures()) {
            simulator.getDeadCreatures().forEach(creature -> creature.draw(g));
        }
        synchronized (simulator.getFoods()) {
            simulator.getFoods().forEach(food -> food.draw(g));
        }
        synchronized (simulator.getCreatures()) {
            simulator.getCreatures().stream().filter(Creature::isAlive).forEach(creature -> creature.draw(g));
        }
    }

}
