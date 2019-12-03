package roksard.big;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public static void main(String[] args) {
        System.out.println(0.2+0.1);
    }
}
