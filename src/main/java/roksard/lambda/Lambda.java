package roksard.lambda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class Lambda {
    public static void main (String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2019, 00, 01);
        c.add(Calendar.DAY_OF_YEAR, 256);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format1.format(c.getTime()));
        System.out.println();
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        list.add("second");
        String s = list.stream().reduce("a", (x, y) -> x+y);
        System.out.println(s);
        Integer in = null;
        if(in == 5) {
            System.out.println("==5");
        } else {
            System.out.println("!=5");
        }
    }
}
