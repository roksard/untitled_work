package roksard.lambda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Reducer_ {
    static void testReduce1() {
        System.out.println("\ntestReduce1");
        List<BigDecimal> list = Arrays.asList(BigDecimal.valueOf(15), BigDecimal.valueOf(20), BigDecimal.valueOf(100));
        BigDecimal sum = list.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        System.out.println(sum);
    }

    static void testReduceWhenNull() {
        System.out.println("\ntestReduceWhenNull");
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), null, BigDecimal.valueOf(3));
        BigDecimal result = null;
        /* #1:
            a = 0 | b = 1
            a = 1 | b = null
            Exception in thread "main" java.lang.NullPointerException */
        result = numbers.stream().reduce(BigDecimal.ZERO, (a, b) -> {
            System.out.println("a = " + a + " | b = " + b);
            return a.add(b);
        });

        //#2
        //Exception in thread "main" java.lang.NullPointerException
        result = numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        testReduce1();
        testReduceWhenNull();
    }
}
