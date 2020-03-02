package roksard.collections.Map;

import java.util.HashMap;
import java.util.Map;

public class MapGetOrDefault {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vasiliy");
        System.out.println(map.getOrDefault("name", "NoName"));
        System.out.println(map.getOrDefault("surname", "NoSurname"));
    }
}
