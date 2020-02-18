package roksard.date_periods;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LocalDate date = DateUtils.asLocalDate(new Date());
        Set<LocalDate> set = new HashSet<>();
        for (int i = 0; i < 10; i++){
            set.add(date);
            date = date.plusDays(1);
        }
        System.out.println(date);
        System.out.println(set);
    }
}
