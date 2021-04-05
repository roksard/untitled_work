package roksard.algorithms.sort;

import roksard.algorithms.sort.sorters.InsertionSort;
import roksard.algorithms.sort.sorters.InsertionSortSimple;

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


        Data data = new Data(100);
        System.out.println(Arrays.toString(data.getArray()));
        new InsertionSort(data, sortPanel, 1).sort();
        data.refillArray();
        new InsertionSortSimple(data, sortPanel, 1).sort();
//        new BubbleSort(data, sortPanel, 1).sort();
//        new SelectionSort(data, sortPanel, 1).sort();
    }
}
