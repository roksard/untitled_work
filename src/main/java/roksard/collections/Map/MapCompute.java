package roksard.collections.Map;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapCompute {
    public static void main(String[] args) {
        Map<Boolean, BigDecimal> map = new HashMap<>();
        //map.put(1, BigDecimal.TEN);
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            boolean flag = rand.nextBoolean();
            BigDecimal value = BigDecimal.valueOf(i);
            System.out.println(flag + ": " + i);
            map.compute(flag, (key, current) -> current == null ? value : current.add(value));
        }
        System.out.println(map);
    }
}
