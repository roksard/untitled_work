package roksard.logarithmic_volume;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void printVol(int vol, double lvol) {
        if (vol % 10 != 0) {
            return;
        }
        System.out.format("%3d = %6.2f%% ", vol, lvol);
        for (int i = 0; i < lvol; i++) {
            System.out.print(".");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Double> volList = new ArrayList<>();
        for(int vol = 0; vol <= 100; vol++) {
            double lvol = Math.pow(vol, 2) / 100;
            printVol(vol, lvol);
        }
    }
}
