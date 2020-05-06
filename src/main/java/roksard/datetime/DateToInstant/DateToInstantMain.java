package roksard.datetime.DateToInstant;

import java.sql.Date;
import java.time.Instant;

public class DateToInstantMain {
    public static void main(String[] args) {
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(now.getTime());
        System.out.println(now);
        System.out.println(sqlDate);
        System.out.println(sqlTimestamp);

        Instant instant = Instant.now();
        Instant instant2 = now.toInstant();
        //instant2 = sqlDate.toInstant();       //UnsupportedOperationException
        //instant2 = sqlTimestamp.toInstant();  //ok
        Instant instant3 = Instant.ofEpochMilli(now.getTime());
        Instant instant4 = Instant.ofEpochMilli(sqlDate.getTime());
        Instant instant5 = Instant.ofEpochMilli(sqlTimestamp.getTime());
        System.out.println("instant: " + instant);
        System.out.println("instant2: " + instant2);
        System.out.println("instant3: " + instant3);
        System.out.println("instant4: " + instant4);
        System.out.println("instant5: " + instant5);
        java.util.Date dateFromInstant = java.util.Date.from(instant);
        System.out.println("Date.from(instant): " + dateFromInstant);
        System.out.println();
    }
}
