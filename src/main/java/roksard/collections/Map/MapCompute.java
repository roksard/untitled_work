package roksard.collections.Map;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapCompute {
    public static void main(String[] args) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        //map.put(1, BigDecimal.TEN);
        BigDecimal value = BigDecimal.valueOf(15);
        map.compute(1, (Integer key, BigDecimal current) -> current == null ? value : value.add(current));
        System.out.println(map);
    }
}
