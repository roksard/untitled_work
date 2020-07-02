package roksard.collections.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListsSortEquals {
    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(3, 1, 5, 8, 13);
        List<Integer> listB = new ArrayList<>(listA);
        System.out.println("listA : " + listA);
        System.out.println("listB : " + listB);
        System.out.println("listA equals listB ?=" + listA.equals(listB));
        System.out.println("sorting listB now");
        listB.sort(null);
        System.out.println("listA : " + listA);
        System.out.println("listB : " + listB);
        System.out.println("listA equals listB ?=" + listA.equals(listB));

    }
}
