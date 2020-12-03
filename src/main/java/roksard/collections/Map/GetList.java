package roksard.collections.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetList {


    public static void main(String[] args) {
        Map<String, List<String>> resultMap = new HashMap<>();
        System.out.println("1" + resultMap);

        resultMap.putIfAbsent("key1", new ArrayList<>());
        resultMap.get("key1").add("AAA");
        System.out.println("2" + resultMap);

        resultMap.putIfAbsent("key1", new ArrayList<>());
        resultMap.get("key1").add("BBB");
        System.out.println("3" + resultMap);

        resultMap.putIfAbsent("key2", new ArrayList<>());
        resultMap.get("key2").add("CCC");
        System.out.println("4" + resultMap);
    }
}
