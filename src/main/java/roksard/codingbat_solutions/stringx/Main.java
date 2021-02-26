package roksard.codingbat_solutions.stringx;

/**
            Given a string, return a version where all the "x" have been removed. Except an "x" at the very start or end should not be removed.


        stringX("xxHxix") → "xHix"
        stringX("abxxxcd") → "abcd"
        stringX("xabxxxcdx") → "xabcdx"
*/
public class Main {
    public static String stringX(String str) {
        return str.length() >=2
                ? str.substring(0, 1) //first char
                    + str.substring(1, str.length()-1).replaceAll("[xX]+", "")
                    + str.substring(str.length()-1) //last char
                : str;
    }

    public static void main(String[] args) {
        System.out.println(stringX("xaxbxcxXxdxexfx"));
    }
}
