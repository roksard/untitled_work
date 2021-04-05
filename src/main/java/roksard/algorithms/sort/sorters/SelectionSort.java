package roksard.algorithms.sort.sorters;

import roksard.algorithms.sort.Data;
import roksard.algorithms.sort.SortPanel;

public class SelectionSort {
    private Data data;
    private SortPanel sortPanel;
    private int sleepInterval = 0;
    public SelectionSort(Data data) {
        this.data = data;
    }

    public SelectionSort(Data data, SortPanel sortPanel, int sleepInterval) {
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
        if (array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minId = i;
            for (int j = i+1; j < array.length; j++) {
                data.setSeekCursor(j);
                if (array[j] < array[minId]) {
                    minId = j;
                    data.setCursor(j);
                }
                callRepaint();
            }
            data.setCursor(i);
            callRepaint();
            int elem = array[i];
            array[i] = array[minId];
            array[minId] = elem;
        }
        callRepaint();
        return array;
    }
}
