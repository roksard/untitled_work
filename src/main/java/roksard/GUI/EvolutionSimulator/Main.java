package roksard.GUI.EvolutionSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        EvolutionSimulator simulator = new EvolutionSimulator(1, 30);

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
                simulator.getCreatures().forEach(creature -> creature.draw(g));
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
