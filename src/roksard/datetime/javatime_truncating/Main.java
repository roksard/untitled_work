package roksard.datetime.javatime_truncating;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void print(Set<Instant> set1, Set<Instant> set2) {
        String emptyS;
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Instant.now().toString().length(); i++) {
                sb.append("-");
            }
            emptyS = sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Instant> it1 = set1.iterator();
        Iterator<Instant> it2 = set2.iterator();
        sb.append(set1.size()).append(": ").append(emptyS).append(" ").append(set2.size()).append(": \n");
        while (it1.hasNext() || it2.hasNext()) {
            if (it1.hasNext()) {
                sb.append(it1.next().toString());
            } else {
                sb.append(emptyS);
            }
            sb.append("  ");
            if (it2.hasNext()) {
                sb.append(it2.next().toString());
            } else {
                sb.append(emptyS);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        Instant a = Instant.now();
        Set<Instant> days = new LinkedHashSet<>(); //uniq intervals not less than 1 day (unique days)
        Set<Instant> daysTest = new LinkedHashSet<>();
        Set<Instant> hours = new LinkedHashSet<>(); //uniq intervals not less than 1 hour
        Set<Instant> hoursTest = new LinkedHashSet<>();
        Set<Instant> minutes = new LinkedHashSet<>(); //uniq invetrvals not less than 1 min each
        Set<Instant> minutesTest = new LinkedHashSet<>();
        for (int i = 0; i < 10; i++) {
            Instant b = a.plus(new Random().nextInt(3600 * 24 * 5), ChronoUnit.SECONDS);
            daysTest.add(b);
            days.add(b.truncatedTo(ChronoUnit.DAYS));
        }
        System.out.println("Days");
        print(daysTest, days);

        for (int i = 0; i < 10; i++) {
            Instant b = a.plus(new Random().nextInt(3600 * 24 * 2), ChronoUnit.SECONDS);
            hoursTest.add(b);
            hours.add(b.truncatedTo(ChronoUnit.HOURS));
        }
        System.out.println("Hours");
        print(hoursTest, hours);

        for (int i = 0; i < 10; i++) {
            Instant b = a.plus(new Random().nextInt(3600 * 2), ChronoUnit.SECONDS);
            minutesTest.add(b);
            minutes.add(b.truncatedTo(ChronoUnit.MINUTES));
        }
        System.out.println("Minutes");
        print(minutesTest, minutes);

    }
}

/*
Output: unique intervals
Days
10: ------------------------ 5:
2020-02-20T23:55:20.960Z  2020-02-20T00:00:00Z
2020-02-21T00:48:09.960Z  2020-02-21T00:00:00Z
2020-02-20T15:23:29.960Z  2020-02-22T00:00:00Z
2020-02-22T01:52:53.960Z  2020-02-18T00:00:00Z
2020-02-21T19:13:09.960Z  2020-02-17T00:00:00Z
2020-02-18T04:13:10.960Z  ------------------------
2020-02-17T16:00:17.960Z  ------------------------
2020-02-22T06:49:21.960Z  ------------------------
2020-02-18T17:18:39.960Z  ------------------------
2020-02-20T04:23:11.960Z  ------------------------

Hours
10: ------------------------ 9:
2020-02-17T09:21:32.960Z  2020-02-17T09:00:00Z
2020-02-17T15:49:02.960Z  2020-02-17T15:00:00Z
2020-02-18T07:26:58.960Z  2020-02-18T07:00:00Z
2020-02-18T21:22:54.960Z  2020-02-18T21:00:00Z
2020-02-18T20:57:50.960Z  2020-02-18T20:00:00Z
2020-02-17T17:02:14.960Z  2020-02-17T17:00:00Z
2020-02-17T17:45:48.960Z  2020-02-17T07:00:00Z
2020-02-17T07:20:17.960Z  2020-02-17T16:00:00Z
2020-02-17T16:49:01.960Z  2020-02-18T03:00:00Z
2020-02-18T03:53:41.960Z  ------------------------

Minutes
10: ------------------------ 9:
2020-02-17T07:29:41.960Z  2020-02-17T07:29:00Z
2020-02-17T07:40:59.960Z  2020-02-17T07:40:00Z
2020-02-17T08:22:52.960Z  2020-02-17T08:22:00Z
2020-02-17T08:22:04.960Z  2020-02-17T07:39:00Z
2020-02-17T07:39:47.960Z  2020-02-17T08:42:00Z
2020-02-17T08:42:18.960Z  2020-02-17T08:59:00Z
2020-02-17T08:59:44.960Z  2020-02-17T07:24:00Z
2020-02-17T07:24:36.960Z  2020-02-17T07:42:00Z
2020-02-17T07:42:40.960Z  2020-02-17T08:08:00Z
2020-02-17T08:08:19.960Z  ------------------------

 */
