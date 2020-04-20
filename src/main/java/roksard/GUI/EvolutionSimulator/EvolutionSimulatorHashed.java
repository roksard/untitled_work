package roksard.GUI.EvolutionSimulator;

import roksard.util.Logger;
import roksard.util.Util;

import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class EvolutionSimulatorHashed extends EvolutionSimulator{
    public ReentrantLock getCreaturesLock() {
        return creaturesLock;
    }

    public ReentrantLock getFoodsLock() {
        return foodsLock;
    }

    ReentrantLock creaturesLock = new ReentrantLock();
    ReentrantLock foodsLock = new ReentrantLock();
    Logger log = Logger.getLogger("EvolutionSimulatorHashed");
    Logger logHoldersListTime = Logger.getLogger("EvolutionSimulatorHashed-HoldersList");
    {
        log.setDisabled(false);
    }
    private Holder[][] holders;
    private final int holdersNumber = 10;
    {
        holders = new Holder[holdersNumber][holdersNumber];
        log.log("Test print holders" + holders[0][0]);
        for(int i = 0; i < holdersNumber; i++) {
            for(int n = 0; n < holdersNumber; n++) {
                holders[i][n] = new Holder();
            }
        }
        log.log("Test print holders" + holders[0][0]);
    }

    class Holder {
        private List<Creature> creatures = new ArrayList<>();
        private List<Food> foods = new ArrayList<>();

        public List<Creature> getCreatures() {
            return Collections.synchronizedList(creatures);
        }

        public List<Food> getFoods() {
            return Collections.synchronizedList(foods);
        }

        @Override
        public String toString() {
            return "<Holder here>";
        }
    }

    public EvolutionSimulatorHashed(int creaturesNumber, int foodNumber, int tick, int deadCreaturesMaxCount) {
        super(0, 0, tick, deadCreaturesMaxCount);
        for(int i = 0; i < foodNumber; i++) {
            addFood(randomFood());
        }
        for(int i = 0; i < creaturesNumber; i++) {
            addCreature(
                    new Creature(this,
                            randomPoint((int) Math.round(fieldSize.getX()), (int) Math.round(fieldSize.getY())),
                            new Dna(5, 10, 20, false)
                    )
            );
        }
        getCreatures().forEach(executorService::execute);
    }

    @Override
    public List<Creature> getCreatures() {
        return Collections.unmodifiableList(creatures);
    }

    @Override
    public List<Food> getFoods() {
        return Collections.unmodifiableList(foods);
    }

    @Override
    public void addCreature(Creature c) {
        synchronized (getCreaturesLock()) {
            creatures.add(c);
        }
        synchronized (this) {
            aliveCreatures.incrementAndGet();
            setCreaturesSize(getCreaturesSize() + Math.pow(c.getDna().size, 2));
        }
        executorService.execute(c);
    }

    public void addCreatures(List<Creature> list) { list.forEach(this::addCreature); }

    public void addFood(Food f) {
        synchronized (getFoodsLock()) {
            foods.add(f);
        }
        getHolderByCoords(f.getLocation()).getFoods().add(f);
    }

    public void addFoods(List<Food> list) {
        list.forEach(this::addFood);
    }

    public void removeFood(Food f) {
        synchronized (getFoodsLock()) {
            foods.remove(f);
        }
        getHolderByCoords(f.getLocation()).getFoods().remove(f);
    }


    public void clearFoods() {
        synchronized (getFoodsLock()) {
            foods.clear();
            for (int i = 0; i < holdersNumber; i++) {
                for (int n = 0; n < holdersNumber; n++) {
                    holders[i][n].getFoods().clear();
                }
            }
        }
    }

    public void clearCreatures() {
        synchronized (getCreaturesLock()) {
            creatures.clear();
            for (int i = 0; i < holdersNumber; i++) {
                for (int n = 0; n < holdersNumber; n++) {
                    holders[i][n].getCreatures().clear();
                }
            }
        }
    }

    public void removeFoodHash(Food f) {
        getHolderByCoords(f.getLocation()).getFoods().remove(f);
    }

    public Holder getHolderByCoords(Point p) {
        int xId = getHolderIdX((int)p.getX());
        int yId = getHolderIdY((int)p.getY());
        return holders[xId][yId];
    }

    public Set<Holder> getHoldersByRange(Point from, double radius) {
        Instant start = Util.timerInit();
        Set<Holder> list = new HashSet<>();
        list.add(getHolderByCoords(from));
        int idXFrom = getHolderIdX((int)from.getX())-1;
        int idYFrom = getHolderIdY((int)from.getY())-1;
        int idXTo = idXFrom+2;
        int idYTo = idYFrom+2;
        int prevSize;
        do {
            prevSize = list.size();
            for(int x = idXFrom; x <= idXTo; x++) {
                for (int y = idYFrom; y <= idYTo; y++) {
                    if (list.contains(holders[x][y])) {
                        continue;
                    }
                    int coordXFrom = x * getHolderSizeX();
                    int coordXTo = (x+1) * getHolderSizeX();
                    int coordYFrom = y * getHolderSizeY();
                    int coordYTo = (y+1) * getHolderSizeY();
                    if (coordXFrom <= (from.getX()+radius) && coordXTo >= (from.getX()-radius)
                        && coordYFrom <= (from.getY()+radius) && coordYTo >= (from.getY()-radius)
                    ) {
                        list.add(holders[x][y]);
                    }
                }
            }
            idXFrom--;
            idYFrom--;
            idXTo++;
            idYTo++;
        } while (prevSize != list.size());
        logHoldersListTime.log(Util.timerDiffNanos(start));
        logHoldersListTime.log(list);
        return list;
    }

    public int getHolderIdX(int coordX) {
        return coordX / getHolderSizeX();
    }

    public int getHolderIdY(int coordY) {
        return coordY / getHolderSizeY();
    }

    public int getHolderSizeX() {
        return (int)Math.round(fieldSize.getX()) / holdersNumber;
    }

    public int getHolderSizeY() {
        return (int)Math.round(fieldSize.getY()) / holdersNumber;
    }

}
