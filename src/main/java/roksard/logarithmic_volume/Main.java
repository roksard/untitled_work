package roksard.logarithmic_volume;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> volList = new ArrayList<>();
        for(int vol = 0; vol <= 100; vol++) {
            double lvol = Math.pow(vol, 2) / 100;
            System.out.format("%.2f%% ", lvol);
            for(int i = 0; i < lvol; i++) {
                System.out.print(".");
            }
            System.out.println();
        }
    }
}
