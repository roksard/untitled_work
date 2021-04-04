package roksard.algorithms.sort;

import javax.swing.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SortPanel sortPanel = new SortPanel();
        frame.add(sortPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();


        Data data = new Data(350);
        System.out.println(Arrays.toString(data.getArray()));
        System.out.println(Arrays.toString(new InsertionSort(data, sortPanel, 30).sort()));
    }
}
