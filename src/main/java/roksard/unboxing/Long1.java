package roksard.unboxing;

import java.util.HashMap;
import java.util.Map;

public class Long1 {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("entityId", new Integer(34));
        Long entityId = ((Integer)map.get("entityId")).longValue();
        System.out.println(entityId);
    }
}
