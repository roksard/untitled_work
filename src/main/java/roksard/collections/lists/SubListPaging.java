package roksard.collections.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubListPaging {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < new Random().nextInt(100)+5; i++) {
//            list.add(i);
//        }

        iterate(list);
    }

    public static void iterate(List<Integer> list) {
        final int maxPage = 1_000_000;
        int pageSize = new Random().nextInt(10)+1;
        System.out.println("pageSize = "+pageSize);
        System.out.println("list = "+list);
        List<Integer> customers = list;
        for (int page = 0; page <= maxPage; page++) {
            int from = page * pageSize;
            int to = (page+1) * pageSize;
            if (to > list.size()) {
                to = list.size();
            }
            if (from > list.size() || from == to) {
                break;
            }
            System.out.println("from " + from);
            System.out.println("to " + to);
            someAction(customers.subList(from, to));
            if (page >= maxPage) {
                throw new RuntimeException("Max page limit exceeded (page >= maxPage)");
            }
        }
    }

    public static void someAction(List<Integer> customers) {
        System.out.println("----------");
        System.out.println(customers);
    }
}
