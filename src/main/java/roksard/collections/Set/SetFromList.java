package roksard.collections.Set;

import java.util.*;

public class SetFromList {
    static void testNullableOptional() {
        System.out.println("\nset from filledList: ");
        List<String> filledList = Arrays.asList("a", "b", "c", "a");
        Set<String> uniqueList = new HashSet<>(Optional.ofNullable(filledList).orElse(Collections.emptyList()));
        System.out.println(uniqueList);
        uniqueList.clear();

        System.out.println("\nset from listWithNull: ");
        List<String> listWithNull = Arrays.asList("a", "b", null, "a");
        uniqueList = new HashSet<>(Optional.ofNullable(listWithNull).orElse(Collections.emptyList()));
        System.out.println(uniqueList);
        uniqueList.clear();

        System.out.println("\nset from emptyList: ");
        List<String> emptyList = Collections.emptyList();
        uniqueList = new HashSet<>(Optional.ofNullable(emptyList).orElse(Collections.emptyList()));
        System.out.println(uniqueList);
        uniqueList.clear();

        System.out.println("\nset from nullList(=null): ");
        List<String> nullList = null;
        uniqueList = new HashSet<>(Optional.ofNullable(nullList).orElse(Collections.emptyList()));
        System.out.println(uniqueList);
        uniqueList.clear();
    }
    public static void main(String[] args) {
        testNullableOptional();
    }
}
