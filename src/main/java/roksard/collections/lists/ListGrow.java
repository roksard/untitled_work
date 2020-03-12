package roksard.collections.lists;

public class ListGrow {
    public static void main(String[] args) {
        for(int i = -10; i < 150; i+= 10) {
            int newCapacity = (i >> 1);
            int newCapacity2 = (i >>> 1);
            System.out.format("%3d >>  1 = %3d\n", i, newCapacity);
            System.out.format("%3d >>> 1 = %3d\n", i, newCapacity2);
            System.out.format("%32s >>  1 = %32s\n", Integer.toBinaryString(i), Integer.toBinaryString(newCapacity));
            System.out.format("%32s >>> 1 = %32s\n", Integer.toBinaryString(i), Integer.toBinaryString(newCapacity2));
        }
    }
}
