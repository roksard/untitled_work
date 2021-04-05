package roksard.algorithms.sort.sorters;

import roksard.algorithms.sort.Data;
import roksard.algorithms.sort.SortPanel;

public class BubbleSort {
    private Data data;
    private SortPanel sortPanel;
    private int sleepInterval = 0;
    public BubbleSort(Data data) {
        this.data = data;
    }

    public BubbleSort(Data data, SortPanel sortPanel, int sleepInterval) {
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
        while (true) {
            int element;
            boolean swapped = false;
            for (int i = 0; i < array.length-1; i++) {
                data.setSeekCursor(i+1);
                if (array[i] > array[i + 1]) {
                    data.setCursor(i);
                    element = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = element;
                    swapped = true;
                }
                callRepaint();
            }
            if (!swapped) {
                break;
            }
        }

        return array;
    }
}
