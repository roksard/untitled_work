package roksard.boolean1;

import java.util.Objects;

public class Booleans {
    public static void main2(String[] args) {
//        System.out.println(Boolean.TRUE.equals(null));
//        System.out.println(Boolean.FALSE.equals(null));
        Boolean xoxo = null;
        boolean abc = Boolean.TRUE.equals(xoxo);
        System.out.println(abc);
        if(xoxo) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Boolean _true = Boolean.TRUE;
        Boolean _null = null;
        System.out.println(Objects.equals(_true, true));
        System.out.println(Objects.equals(_null, true));
        System.out.println(Objects.equals(_null, _true));
    }
}
