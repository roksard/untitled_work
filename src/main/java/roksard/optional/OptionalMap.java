package roksard.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3);
        list = null;
        System.out.println(Optional.ofNullable(list).map(listo -> String.valueOf(listo.get(0))).orElse("-"));
    }
}
