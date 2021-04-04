package roksard.algorithms.sort;

public class InsertionSort {
    private Data data;
    private SortPanel sortPanel;
    private int sleepInterval = 0;
    public InsertionSort(Data data) {
        this.data = data;
    }

    public InsertionSort(Data data, SortPanel sortPanel, int sleepInterval) {
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
        int sortedSize = 0; //also index of next element to take
        int element;

        int[] array = data.getArray();
        while (sortedSize < array.length) {
            data.setCursor(sortedSize);
            callRepaint();
            if (sortedSize > 0) {
                element = array[sortedSize];
                int sortedStart = 0;
                int sortedEnd = sortedSize-1;
                while (sortedEnd - sortedStart > 0) {
                    int pos = (sortedEnd - sortedStart) / 2 + sortedStart;
                    if (element >= array[pos]) {
                        sortedStart = pos+1;
                    } else {
                        sortedEnd = pos-1;
                    }
                    data.setSeekCursor(pos);
                    callRepaint();
                }
                int insertInto = element >= array[sortedStart] ? sortedStart + 1 : sortedStart;
                for (int i = sortedSize; i > insertInto; i--) {
                    array[i] = array[i-1];
                    data.setCursor(i);
                    callRepaint();
                }
                array[insertInto] = element;
            }
            sortedSize += 1;
        }
        callRepaint();
        return array;
    }
}
