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

public class Creature implements Runnable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creature creature = (Creature) o;
        return id == creature.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private static AtomicInteger count = new AtomicInteger(0);
    int id = count.getAndIncrement();

    Logger log = Logger.getLogger(this.toString());
    static Logger logFoodSearchTime = Logger.getLogger("foodSearchTime: ");
    static Logger logCreatureSearchTime = Logger.getLogger("creatureSearchTime: ");
    static ArrayList<Long> totalAvgCST = new ArrayList<>();
    static ArrayList<Long> totalAvgFST = new ArrayList<>();
    {
        log.setDisabled(true);
    }

    final static FixedSizeQueue<Long> foodSearchTime = FixedSizeQueue.synchronizedQueue(new FixedSizeQueue<Long>(100));
    final static FixedSizeQueue<Long> creatureSearchTime = FixedSizeQueue.synchronizedQueue(new FixedSizeQueue<Long>(100));
    static Timer searchTimeLogger = new Timer() {{
        schedule(new TimerTask() {
            @Override
            public void run() {
                long avgFoodSearchTime = 0;
                synchronized (foodSearchTime) {
                    for (long time : foodSearchTime) {
                        avgFoodSearchTime += time;
                    }
                    avgFoodSearchTime = Math.round(avgFoodSearchTime / (double)foodSearchTime.size());
                }
                long avgCreatureSearchTime = 0;
                synchronized (creatureSearchTime) {
                    for (long time : creatureSearchTime) {
                        avgCreatureSearchTime += time;
                    }
                    avgCreatureSearchTime = Math.round(avgCreatureSearchTime / (double)creatureSearchTime.size());
                }
                logFoodSearchTime.log(avgFoodSearchTime);
                logCreatureSearchTime.log(avgCreatureSearchTime);
                totalAvgFST.add(avgFoodSearchTime);
                totalAvgCST.add(avgCreatureSearchTime);
                if (totalAvgFST.size() >= 10) {
                    totalAvgFST.stream().reduce(Long::sum).ifPresent(value -> {
                        logFoodSearchTime.log("total avg: " + Math.round(value / (double)totalAvgFST.size()));
                    });
                    totalAvgCST.stream().reduce(Long::sum).ifPresent(value -> {
                        logCreatureSearchTime.log("total avg: " + Math.round(value / (double)totalAvgCST.size()));
                    });
                    System.exit(1);
                    //avg per 6 runs:
                    //15:49:26.133 --foodSearchTime: --  total avg: 13490833
                    //15:49:26.134 --creatureSearchTime: --  total avg: 444000
                }
            }
        }, 3000, 3000);
    }};


    public void setShape(Shape shape) {
        this.shape = shape;
    }

    Shape shape;
    Point location;
    Random rand = new Random();
    volatile private double energy = 100;
    double reproduceThreshold = 200;
    Dna dna;
    private EvolutionSimulator simulator;
    State state = State.WANDER;
    static enum State {WANDER, HUNT_FOOD, HUNT_CREATURE}
    Food foundFood;
    Creature creatureToEat;
    volatile boolean isAlive = true;
    volatile boolean canReproduce = true;

    @Override
    public String toString() {
        return "c" + id;
    }

    public String toStringEx() {
        StringJoiner j = new StringJoiner("|", "~(", ")")
                .add(String.format("size %.2f", dna.size))
                .add(String.format("speed %.2f", dna.speed))
                .add(String.format("sense %.2f", dna.senseRadius));
        return "c" + String.format("%06d", id) + j.toString();
    }

    public String toCSV() {
        StringJoiner j = new StringJoiner(";")
                .add(String.format("%.2f", dna.size))
                .add(String.format("%.2f", dna.speed))
                .add(String.format("%.2f", dna.senseRadius));
        return j.toString();
    }

    public Creature(EvolutionSimulator es, Point location, Dna dna) {
        this(es, 100, location, dna);
    }

    public Creature(EvolutionSimulator es, double energy, Point location, Dna dna) {
        this(es, energy, location, dna, 200);
    }

    public Creature(EvolutionSimulator es, double energy, Point location, Dna dna, double reproduceThreshold) {
        this.simulator = es;
        this.location = new Point(location);
        this.dna = dna;
        this.shape = new Circle(location, dna.size, Color.RED);
        this.energy = energy;
        this.reproduceThreshold = reproduceThreshold;
    }

    void updateShape() {
        if (!isAlive()) {
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

    Optional<Food> findClosestFood() {
        Instant start = Util.timerInit();
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
        foodSearchTime.offer(Util.timerDiffNanos(start));
        return Optional.ofNullable(minF);

    }

    public Point getLocation() {
        return location;
    }

    boolean isSmaller(Creature other) {
        return (this.dna.size + this.dna.size) <= other.dna.size;
    }

    Optional<Creature> findCreatureToEat() {
        Instant start = Util.timerInit();
        double min = -1;
        Creature minF = null;
        synchronized (simulator.getCreatures()) {
            for (Creature creature : simulator.getCreatures()) {
                double dist = creature.getLocation().distance(this.location);
                if (creature.isAlive() && creature.isSmaller(this) && (min == -1 || dist < min)) {
                    min = dist;
                    minF = creature;
                }
            }
        }
        if (min > dna.senseRadius) {
            minF = null;
        }
        creatureSearchTime.offer(Util.timerDiffNanos(start));
        return Optional.ofNullable(minF);
    }



    void walk() {
        double newX, newY;
        double deltaX = Util.randomDouble(-dna.speed, dna.speed, rand);
        double deltaY = Util.randomDouble(-dna.speed, dna.speed, rand);
        newX = rangeLimit(0, simulator.getFieldSize().getX(), location.getX() + deltaX);
        newY = rangeLimit(0, simulator.getFieldSize().getY(), location.getY() + deltaY);
        location.setLocation(newX, newY);
        addEnergy(walkEnergy());
        updateShape();
    }

    void walk(Point location) {
        if (this.location.distance(location) <= dna.speed) {
            this.location.setLocation(location);
        } else {
            this.location.setLocation(GeometryUtils.getPointOnVector(this.location, location, dna.speed));
        }
        addEnergy(walkEnergy());
        updateShape();
    }

    double walkEnergy() {
        return -this.dna.size * this.dna.speed * this.dna.speed * 0.005;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        if (isAlive() && !alive) {
            synchronized (simulator) {
                simulator.getAliveCreatures().decrementAndGet();
                simulator.setCreaturesSize(simulator.getCreaturesSize() - Math.pow(this.getDna().size, 2));
                synchronized (simulator.getDeadCreatures()) {
                    simulator.getDeadCreatures().offer(this);
                }
            }
        }
        synchronized (simulator.getCreatures()) {
            isAlive = alive;
        }
        updateShape();
    }

    void addEnergy(double deltaEnergy) {
        energy += deltaEnergy;
        if (energy <= 0) {
            synchronized (simulator.getCreatures()) {
                setAlive(false);
            }
            updateShape();
        } else if (energy > reproduceThreshold) {
            Dna newDna = new Dna(dna);
            final double mutationMultiplier = 0.2; //how strong a mutation is against current value
            final int mutationProbability = 50; //in % approx
            int mutation = rand.nextInt(100 / mutationProbability + 3);

            if (mutation == 1) {
                newDna.speed = rangeLimit(0.01, 50, newDna.speed
                        + Util.randomDouble(-newDna.speed * mutationMultiplier, newDna.speed * mutationMultiplier, rand));
            }
            if (mutation == 2) {
                newDna.senseRadius = rangeLimit(0, 1000, newDna.senseRadius
                        + Util.randomDouble(-newDna.senseRadius * mutationMultiplier, newDna.senseRadius * mutationMultiplier, rand));
            }
            if (mutation == 3) {
                newDna.size = rangeLimit(0.01, 30, newDna.size
                        + Util.randomDouble(-newDna.size * mutationMultiplier, newDna.size * mutationMultiplier, rand));
            }
            if (canReproduce) {
                double energyForNew = energy / 2;
                Creature newBorn = new Creature(simulator, energyForNew, location, newDna, newDna.size * 20);
                log.log("new creature " + newBorn.toStringEx());
                simulator.addCreature(newBorn);
                addEnergy(-energyForNew);
                if (simulator.getCreatureDensity() > 1) {
                    setAlive(false);
                }
            }
        }
    }

    boolean tryFindFood() {
        Optional<Food> closestFood = findClosestFood();
        closestFood.ifPresent(food -> {
            state = State.HUNT_FOOD;
            foundFood = food;
        });
        return closestFood.isPresent();
    }

    boolean tryFindCreatureAsFood() {
        Optional<Creature> closestCTE = findCreatureToEat();
        closestCTE.ifPresent(creature -> {
            state = State.HUNT_CREATURE;
            this.creatureToEat = creature;
        });
        return closestCTE.isPresent();
    }
    void wander() {
        walk();
        if (dna.isHunter) {
            if (!tryFindCreatureAsFood()) {
                tryFindFood();
            }
        } else {
            if (!tryFindFood()) {
                tryFindCreatureAsFood();
            }
        }
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
        synchronized (simulator.getCreatures()) {
            keepHunting = creatureToEat.isAlive() && creatureToEat.location.distance(location) <= dna.senseRadius;
        }
        if (!keepHunting) {
            creatureToEat = null;
            state = State.WANDER;
        } else {
            if (creatureToEat.location.distance(location) <= this.dna.size / 2 ) {
                //eat
                double energyToAdd = 0;
                synchronized (simulator.getCreatures()) {
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

    @Override
    public void run() {
        try {
            while (!Thread.interrupted() && isAlive()) {
                Thread.sleep(simulator.tick);
                if (state == State.WANDER) {
                    wander();
                } else if (state == State.HUNT_FOOD) {
                    huntFood();
                } else if (state == State.HUNT_CREATURE) {
                    huntCreature();
                }
            }
        } catch (Throwable e) {

        }
    }

    public Dna getDna() {
        return dna;
    }
}
