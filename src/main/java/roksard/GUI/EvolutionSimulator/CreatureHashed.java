package roksard.GUI.EvolutionSimulator;

import roksard.GUI.EvolutionSimulator.gui.Circle;
import roksard.GUI.EvolutionSimulator.gui.Shape;
import roksard.util.FixedSizeQueue;
import roksard.util.GeometryUtils;
import roksard.util.Logger;
import roksard.util.Util;

import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static roksard.util.Util.rangeLimit;

public class CreatureHashed extends Creature {
    private EvolutionSimulatorHashed simulator;
    private CreatureHashed creatureToEat;

    public CreatureHashed(EvolutionSimulatorHashed es, Point location, Dna dna) {
        this(es, 100, location, dna);
    }

    public CreatureHashed(EvolutionSimulatorHashed es, double energy, Point location, Dna dna) {
        this(es, energy, location, dna, 200);
    }

    public CreatureHashed(EvolutionSimulatorHashed es, double energy, Point location, Dna dna, double reproduceThreshold) {
        super(es, energy, location, dna, reproduceThreshold);
        this.simulator = es;
    }

    Optional<Food> findClosestFood() {
        Instant start = Util.timerInit();
        double min = -1;
        Food minF = null;
        synchronized (simulator.getFoodsLock()) {
            Set<EvolutionSimulatorHashed.Holder> holders = simulator.getHoldersByRange(location, dna.senseRadius);
            for (EvolutionSimulatorHashed.Holder holder : holders) {
                for (Food food : holder.getFoods()) {
                    double dist = food.getLocation().distance(this.getLocation());
                    synchronized (food) {
                        if (food.isExists() && (min == -1 || dist < min)) {
                            min = dist;
                            minF = food;
                        }
                    }
                }
            }
        }
        if (min > getDna().senseRadius) {
            minF = null;
        }
        foodSearchTime.offer(Util.timerDiffNanos(start));
        return Optional.ofNullable(minF);

    }

    Optional<Creature> findCreatureToEat() {
        Instant start = Util.timerInit();
        double min = -1;
        Creature minF = null;
        synchronized (simulator.getCreaturesLock()) {
            for (Creature creature : simulator.getCreatures()) {
                double dist = creature.getLocation().distance(this.location);
                if (creature.isAlive() && creature.isSmaller(this) && (min == -1 || dist < min)) {
                    min = dist;
                    minF = creature;
                }
            }
        }
        if (min > getDna().senseRadius) {
            minF = null;
        }
        creatureSearchTime.offer(Util.timerDiffNanos(start));
        return Optional.ofNullable(minF);
    }


    void huntFood() {
        if (foundFood == null) {
            return;
        }
        boolean foodExists;
        synchronized (foundFood) {
            foodExists = foundFood.isExists();
        }
        if (!foodExists) {
            foundFood = null;
            state = State.WANDER;
        } else {
            if (foundFood.getLocation().distance(this.location) < dna.size / 2) {
                //eat
                foundFood.lock.lock();
                try {
                    if (foundFood.isExists()) {
                        foundFood.setExists(false);
                        simulator.removeFoodHash(foundFood);
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

    void huntCreature() {
        if (creatureToEat == null) {
            return;
        }
        boolean keepHunting;
        synchronized (simulator.getCreaturesLock()) {
            keepHunting = creatureToEat.isAlive() && creatureToEat.location.distance(location) <= dna.senseRadius;
        }
        if (!keepHunting) {
            creatureToEat = null;
            state = State.WANDER;
        } else {
            if (creatureToEat.location.distance(location) <= this.dna.size / 2 ) {
                //eat
                double energyToAdd = 0;
                synchronized (simulator.getCreaturesLock()) {
                    if (creatureToEat.isAlive()) {
                        creatureToEat.setAlive(false);
                        energyToAdd = creatureToEat.dna.size * 20;
                    }
                }
                state = State.WANDER;
                addEnergy(energyToAdd);
                creatureToEat = null;
            } else {
                //move towards cre
                walk(creatureToEat.location);
            }
        }
    }

}
