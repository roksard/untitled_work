package roksard.GUI.EvolutionSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        EvolutionSimulator simulator = new EvolutionSimulator(10, 400);

        JFrame frame = new JFrame();
        frame.add(new JPanel() {
            {
                setBackground(Color.BLACK);
                setPreferredSize(new Dimension(400, 400));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                simulator.getFoods().forEach(food -> food.draw(g));
                synchronized (simulator.getCreatures()) {
                    simulator.getCreatures().sort(new Comparator<Creature>() {
                        @Override
                        public int compare(Creature o1, Creature o2) {
                            return Boolean.compare(o1.isAlive, o2.isAlive);
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

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
            }
        }, 100, 10);
    }
}
