package roksard.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date1 {
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(new Date().getTime()     ));
    }
}
