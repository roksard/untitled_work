package roksard.nulls;

import java.util.ArrayList;
import java.util.List;

public class ListContainsNull {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        System.out.println("1:" + list.contains(5));
        list.add(5);
        System.out.println("2:" + list.contains(5));

        System.out.println("3:" + list.contains(null));
        list.add(null);
        System.out.println("4:" + list.contains(null));
    }
}
