package roksard.GUI.EvolutionSimulator;

import roksard.GUI.EvolutionSimulator.gui.Circle;
import roksard.GUI.EvolutionSimulator.gui.EntityMaler;
import roksard.GUI.EvolutionSimulator.gui.EntityMalerNoSort;
import roksard.GUI.EvolutionSimulator.gui.MainWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EvolutionSimulatorHashed simulator = new EvolutionSimulatorHashed(5, 500, 1, 300);
        EntityMaler maler = new EntityMalerNoSort(simulator);
        MainWindow mainWindow = new MainWindow(maler);

        List<Timer> timers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            timers.add(new Timer());
        }
        int tid = -1;
        timers.get(++tid).schedule(new TimerTask() {
            @Override
            public void run() {
                mainWindow.repaint();
            }
        }, 100, 10);

        AtomicInteger foodTime = new AtomicInteger(0);
        timers.get(++tid).schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(foodTime.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (simulator.getFoodsLock()) {
                    for (int i = 0; i < 10; i++) {
                        simulator.addFood(simulator.randomFood());
                    }
                }
            }
        }, 0, simulator.tick*30);

        timers.get(++tid).schedule(new TimerTask() {
            @Override
            public void run() {
                foodTime.incrementAndGet();
            }
        }, 0, simulator.tick * 1000);

        timers.get(++tid).schedule(new TimerTask() {
            @Override
            public void run() {
                List<Food> newFood;
                synchronized (simulator.getFoodsLock()) {
                    newFood = simulator.getFoods().stream()
                            .filter(food -> food.isExists())
                            .collect(Collectors.toList());
                }
                simulator.clearFoods();
                simulator.addFoods(newFood);

                synchronized (simulator.getCreaturesLock()) {
                    List<Creature> newCreatures = simulator.getCreatures().stream()
                            .filter(creature -> creature.isAlive())
                            .collect(Collectors.toList());
                    simulator.clearCreatures();
                    if (newCreatures.size() == 0) {
                        simulator.log.log("stopping simulator");
                        simulator.shutdownNow();
                        timers.forEach(Timer::cancel);
                    }
                    simulator.addCreatures(newCreatures);
                }
            }
        }, 10000, 10000);

//        timers.get(++tid).schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Creature monster = new Creature(simulator, 4000000, new Point(200, 200), new Dna(10, 20, 100, true));
//                monster.canReproduce = false;
//                monster.setShape(new Circle(monster.getLocation(), 20, Color.YELLOW));
//                simulator.addCreature(monster);
//            }
//        }, 5000);

        timers.get(++tid).schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (simulator) {
                    System.out.println("alive creatures: " + simulator.getAliveCreatures().get());
                    System.out.println("creature density: " + simulator.getCreatureDensity());
                }
            }
        }, 5000, 5000);
    }
}
