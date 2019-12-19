package roksard.lambda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Reducer_ {
    public static void main(String[] args) {
        List<BigDecimal> list = Arrays.asList(BigDecimal.valueOf(15), BigDecimal.valueOf(20), BigDecimal.valueOf(100));
        BigDecimal sum = list.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        System.out.println(sum);
    }
}
