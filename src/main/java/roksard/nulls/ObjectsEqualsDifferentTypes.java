package roksard.nulls;

import java.util.Objects;

public class ObjectsEqualsDifferentTypes{
    public static void main(String[] args) {
        Long long_ = null;
        Integer int_ = null;
        System.out.println("1: " + Objects.equals(long_, int_));

        long_ = 5L;
        int_ = null;
        System.out.println("2: " + Objects.equals(long_, int_));

        long_ = null;
        int_ = 5;
        System.out.println("3: " + Objects.equals(long_, int_));

        long_ = 5L;
        int_ = 5;
        System.out.println("4: " + Objects.equals(long_, int_));

        long_ = 5L;
        int_ = 5;
        System.out.println("5: " + Objects.equals(long_.intValue(), int_));

        long_ = 5L;
        int_ = 5;
        System.out.println("6: " + Objects.equals(long_, int_.longValue()));


    }
}
