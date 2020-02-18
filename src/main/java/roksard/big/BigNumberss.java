package roksard.big;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigNumberss {
    public static void main2(String[] args) {
        //Long long1 = new Long(null);
        //System.out.println(long1);

        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.valueOf(2);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(b) > 0); //greater than
    }

    public static void main3(String[] args) {
        System.out.println(0.2+0.1);
    }

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(0.134);
        BigDecimal b = BigDecimal.valueOf(0.13);
        BigDecimal c = a.setScale(2, RoundingMode.HALF_UP);
        BigDecimal d = b.setScale(2, RoundingMode.HALF_UP);
        for(int i = -5; i < 5; i++) {
            System.out.println("(" + i + ").signum = " + BigDecimal.valueOf(i).signum());
        }
        /* Output:
            (-5).signum = -1
            (-4).signum = -1
            (-3).signum = -1
            (-2).signum = -1
            (-1).signum = -1
            (0).signum = 0
            (1).signum = 1
            (2).signum = 1
            (3).signum = 1
            (4).signum = 1
         */
        System.out.println(c.compareTo(d));
    }
}
