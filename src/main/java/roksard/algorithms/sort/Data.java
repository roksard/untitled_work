package roksard.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

public class Data {
    private int[] array;
    private int maxValue;
    private int minValue;
    private int cursor = -1;
    private int seekCursor = -1;
    private Random random = new Random();

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }



    public Data(int size) {
        array = new int[size];
        minValue = 0;
        maxValue = size-1;
        refillArray();
    }

    public void refillArray() {
        cursor = -1;
        seekCursor = -1;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxValue-minValue+1) + minValue;
            if (array[i] == 9) {
            }
        }
    }

    @Override
    public String toString() {
        return "Entity{"+ Arrays.toString(array) +"}";
    }

    public int[] getArray() {
        return array;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getSeekCursor() {
        return seekCursor;
    }

    public void setSeekCursor(int seekCursor) {
        this.seekCursor = seekCursor;
    }
}
