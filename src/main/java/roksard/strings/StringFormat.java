package roksard.strings;

import java.sql.SQLOutput;
import java.util.StringJoiner;

public class StringFormat {
    static void formatStringColumns() {
        String s1 = "";
        String s2 = "abcdef";
        String s3 = s1+s2;
        int minWidth = 10;
        int maxWidth = 2;
        String params = "%" + minWidth + "." + maxWidth + "s";
        String format = new StringJoiner(" | ").add(params).add(params).add(params).toString();
        System.out.println(String.format(format, s1, s2, s3));
        System.out.println(String.format(format, s2, s3, s1));
        System.out.println(String.format(format, s3, s1, s2));
    }

    static void formatInt() {
        String formatted1 = String.format("%2d", 5);
        System.out.println("formatted1 = '" + formatted1 + "'");

        String formatted2 = String.format("%02d", 5);
        System.out.println("formatted2 = '" + formatted2 + "'");
    }
    public static void main(String[] args) {
        formatInt();
    }
}
