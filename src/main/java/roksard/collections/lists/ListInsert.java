package roksard.collections.lists;

import java.util.ArrayList;
import java.util.List;

public class ListInsert {
    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        list.add(0, 'a');
        list.add(0, 'b');
        System.out.println(list);
        System.out.println(list.get(0));
    }
}
