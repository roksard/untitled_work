package roksard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Generates String value based on int value and given character set (default constructor uses 'A'..'Z' characters)
 * can be used to translate 10-base int values into N-base values with custom characters
 * Base size is determined from size of given to constructor list of characters (List<Character> .size())
 *
 * Example usage:
 * new NameFromInt(3, Arrays.asList('0','1')).getName(); //produces '0011'  String which is a binary(base=2) representation of integer 3 in base=10 system
 *
 */
public class NameFromInt {
    public static List<Character> numeric = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
    private List<Character> chars = new ArrayList<>();
    private String name;

    public NameFromInt(int intValue) {
        for (char c = 'A'; c <= 'Z'; c++) {
            chars.add(c);
        }
        generateName(intValue);
    }

    public NameFromInt(int intValue, List<Character> customCharacterSet) {
        chars.addAll(customCharacterSet);
        generateName(intValue);
    }


    private void generateName(int intValue) {
        StringBuilder nameBuilder = new StringBuilder();
        int base = chars.size();
        int pow = 0;
        while (true) {
            int scale = (int)Math.round(Math.floor(Math.pow(base, pow)));
            int value = (intValue % (base * scale)) / scale % base;
            pow++;
            nameBuilder.append(chars.get(value));
            if (scale*base > intValue) {
                break;
            }
        }
        nameBuilder.reverse();
        name = nameBuilder.toString();
    }


    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int value = rand.nextInt(1000000);
            System.out.print("value: "+value +" / generated: ");
            System.out.println(new NameFromInt(value).getName());
        }
    }
}
