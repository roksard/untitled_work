package roksard.nulls;

import sun.awt.datatransfer.DataTransferer;

import java.math.BigDecimal;
import java.util.Objects;

public class Nulls {
    public static int compare(Object a, Object b) {
        return (a == b) ? 0 : 1;
    }
    public static void main0(String[] args) {
        //DataTransferer.IndexedComparator
        //System.out.println(compare(null, ));
    }
    public static void main1(String[] args) {
        Long lng = 5L;
        Long lng2 = null;

        int c = lng != null ? lng.compareTo(lng2) : -2;
        System.out.println(c);


    }

    public static void main2(String[] args) {
        Short s = null;
        System.out.println(s == 1);
    }

    public static void main(String[] args) {
        Integer a = null;
        Integer b = new Integer(30);
        Integer c = new Integer(30);
        Integer d = 30;
        System.out.println(Objects.equals(a, b));
        System.out.println(Objects.equals(b, c));
        System.out.println(Objects.equals(c, d));
    }
}