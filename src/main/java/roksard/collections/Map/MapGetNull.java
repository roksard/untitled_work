package roksard.collections.Map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

enum Enum {
    A(1, "a"),
    B(2, "b"),
    C(3, "c");

    private int id;
    private String name;
    private final static Map<Integer,Enum> MAP;

    static {
        Map<Integer,Enum> map = new HashMap<>();
        for (Enum value : Enum.values()) {
            map.put(value.getId(), value);
        }
        MAP = Collections.unmodifiableMap(map);
    }

    Enum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    static public Enum getById(int id) {
        return MAP.get(id);
    }

    static public Enum getByIdNullsafe(Integer id) {
        return MAP.get(id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class MapGetNull {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        System.out.println(map.get("a"));
        System.out.println(map.get(null));

        //Enum test
        System.out.println("\nEnum test: ");
        System.out.println(Enum.getById(1));
        System.out.println(Enum.getById(2));
        System.out.println(Enum.getById(3));
        Integer n = null;
        System.out.println("nullsafe: " + Enum.getByIdNullsafe(n));
        System.out.println(Enum.getById(n));
    }
}
