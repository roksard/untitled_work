package roksard.GUI.EvolutionSimulator.gui;

import roksard.GUI.EvolutionSimulator.Creature;
import roksard.GUI.EvolutionSimulator.EvolutionSimulator;
import roksard.GUI.EvolutionSimulator.EvolutionSimulatorHashed;

import java.awt.*;
import java.util.Comparator;

public class EntityMalerNoSort extends EntityMaler {
    EvolutionSimulatorHashed simulator;
    public EntityMalerNoSort(EvolutionSimulatorHashed simulator) {
        super(simulator);
        this.simulator = simulator;
    }

    @Override
    public void draw(Graphics g) {
        synchronized (simulator.getDeadCreatures()) {
            simulator.getDeadCreatures().forEach(creature -> creature.draw(g));
        }
        synchronized (simulator.getFoodsLock()) {
            simulator.getFoods().forEach(food -> food.draw(g));
        }
        synchronized (simulator.getCreaturesLock()) {
            simulator.getCreatures().stream().filter(Creature::isAlive).forEach(creature -> creature.draw(g));
        }
    }

}
