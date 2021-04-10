package roksard.fractals;

import roksard.fractals.impl.KochCurve;
import roksard.fractals.impl.Sierpinski;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new KochCurve(1.05));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();

    }
}
