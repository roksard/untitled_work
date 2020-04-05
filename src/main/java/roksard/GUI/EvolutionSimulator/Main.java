package roksard.GUI.EvolutionSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Timer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EvolutionSimulator simulator = new EvolutionSimulator(5, 200);

        JFrame frame = new JFrame() {
            @Override
            protected void processEvent(AWTEvent e) {
                super.processEvent(e);
                if (WindowEvent.WINDOW_ACTIVATED == e.getID()) {
                    synchronized (simulator.getFoods()) {
//                        System.out.println(simulator.getFoods());
                    }
                    synchronized (simulator.getCreatures()) {
//                        System.out.println(simulator.getCreatures());
                    }
                }
            }
        };
        frame.add(new JPanel() {
            {
                setBackground(Color.BLACK);
                setPreferredSize(new Dimension(400, 400));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
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
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();

        List<Timer> timers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            timers.add(new Timer());
        }
        int tid = 0;
        timers.get(tid++).schedule(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
            }
        }, 100, 10);

        AtomicInteger foodTime = new AtomicInteger(0);
        timers.get(tid++).schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(foodTime.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (simulator.getFoods()) {
                    for (int i = 0; i < 10; i++) {
                        simulator.getFoods().add(simulator.randomFood());
                    }
                }
            }
        }, 0, simulator.tick);

        timers.get(tid++).schedule(new TimerTask() {
            @Override
            public void run() {
                foodTime.incrementAndGet();
            }
        }, 0, simulator.tick * 100000);

        timers.get(tid++).schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (simulator.getFoods()) {
                    List<Food> newFood = simulator.getFoods().stream()
                            .filter(food -> food.isExists())
                            .collect(Collectors.toList());
                    simulator.getFoods().clear();
                    simulator.getFoods().addAll(newFood);
                }
                synchronized (simulator.getCreatures()) {
                    List<Creature> newCreatures = simulator.getCreatures().stream()
                            .filter(creature -> creature.isAlive())
                            .collect(Collectors.toList());
                    simulator.getCreatures().clear();
                    if (newCreatures.size() == 0) {
                        simulator.log.log("stopping simulator");
                        simulator.shutdownNow();
                        timers.forEach(Timer::cancel);
                    }
                    simulator.getCreatures().addAll(newCreatures);
                }
            }
        }, simulator.tick * 120 + 20000, simulator.tick * 120 + 20000);

//        timers.get(tid++).schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Creature monster = new Creature(simulator, 4000000, new Point(200, 200), new Dna(10, 20, 50));
//                monster.canReproduce = false;
//                monster.setShape(new Circle(monster.getLocation(), 20, Color.YELLOW));
//                simulator.addCreature(monster);
//            }
//        }, 5000);

        timers.get(tid++).schedule(new TimerTask() {
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
