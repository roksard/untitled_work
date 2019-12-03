package roksard.stringjoiner;

import java.util.StringJoiner;



public class StringJoiner1 {
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(", ");

        String city = "Perm";
        String street = "Пушкина";
        String house = "9";
        String apt = "apt 367";
        String empty = "";

        String[] array = {
                city,
                isEmpty(street) ? empty : "улица " + street,
                isEmpty(house) ? empty : "дом " + house,
                apt
        };

        for (String part : array) {
            if (!isEmpty(part)) {
                joiner.add(part);
            }
        }

        System.out.println(joiner.toString());
        System.out.println("isempty: " + isEmpty(""));
    }
}
