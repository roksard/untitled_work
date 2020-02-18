package roksard.collections.lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListsCombine {
    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = Arrays.asList(3, 4, 5);
        System.out.println(Stream.concat(
                listA.stream(),
                listB.stream()
        ).collect(Collectors.toSet()));
    }

}
