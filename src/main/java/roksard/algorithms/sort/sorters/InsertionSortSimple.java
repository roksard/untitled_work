package roksard.algorithms.sort.sorters;

import roksard.algorithms.sort.Data;
import roksard.algorithms.sort.SortPanel;

/**
 * Simplest version of algorhytm
 */
public class InsertionSortSimple {
    private Data data;
    private SortPanel sortPanel;
    private int sleepInterval = 0;
    public InsertionSortSimple(Data data) {
        this.data = data;
    }

    public InsertionSortSimple(Data data, SortPanel sortPanel, int sleepInterval) {
        this.data = data;
        this.sortPanel = sortPanel;
        this.sleepInterval = sleepInterval;
    }

    private void callRepaint() {
        if (sortPanel != null) {
            sortPanel.setData(data);
            sortPanel.repaint();
            if (sleepInterval > 0) {
                try {
                    Thread.sleep(sleepInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int[] sort() {
        int[] array = data.getArray();

        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j-1]) {
                data.setCursor(j);
                callRepaint();
                int elem = array[j];
                array[j] = array[j-1];
                array[j-1] = elem;
                j--;
            }
        }

        callRepaint();
        return array;
    }
}
