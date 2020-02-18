package roksard.sets_math;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetsMath {
    public static int days(int a, int b) {
        return Math.abs(a - b) + 1;
    }

    public static void main(String[] args) {
        int cd = 4;
        int now = 5;
        int es = 3;

        int result = 0;
        if (now >= es) {
            result = cd > es ? days(cd, now) : days(es, now);
        }

        System.out.println(result);
    }
}
