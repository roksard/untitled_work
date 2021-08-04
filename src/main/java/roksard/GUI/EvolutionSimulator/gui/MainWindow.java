package roksard.GUI.EvolutionSimulator.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow {
    EntityMaler entityMaler;
    JFrame frame;
    public MainWindow(EntityMaler entityMaler, MouseListener mouseListener) {
        this.entityMaler = entityMaler;
        frame = new JFrame();
        frame.addMouseListener(mouseListener);
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
