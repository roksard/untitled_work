package roksard.GUI.EvolutionSimulator.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    EntityMaler entityMaler;
    JFrame frame;
    public MainWindow(EntityMaler entityMaler) {
        this.entityMaler = entityMaler;
        frame = new JFrame();
        frame.add(new JPanel() {
            {
                setBackground(Color.BLACK);
                setPreferredSize(new Dimension(400, 400));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                entityMaler.draw(g);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }

    public void repaint() {
        frame.repaint();
    }
}
