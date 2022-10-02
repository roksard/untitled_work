package roksard.codingbat_solutions.warmup2.stringmatch;

import java.util.HashSet;

public class StringMatch {
    public int stringMatch(String a, String b) {
        int count = 0;
        for(int i = 0; i < Math.min(a.length(), b.length())-1; i++) {
            if (a.charAt(i) == b.charAt(i) && a.charAt(i+1) == b.charAt(i+1)) {
                count++;
            }
        }
        return count;
    }

    public int stringMatch_v1(String a, String b) {
        final int subLen = 2;
        HashSet<String> subs = new HashSet<String>();
        for(int i = 0; i <= (a.length()-subLen); i++) {
            subs.add(a.substring(i, i+subLen));
        }
        int counter = 0;
        for(String sub : subs) {
            if(b.contains(sub))
                counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        StringMatch obj = new StringMatch();
        int value = obj.stringMatch("aabbccdd", "abbbxxd");
        int expected = 1;
        int testId = 1;
        System.out.println("test" + testId + ":" + (value == expected ? "correct" : String.format("error: expected %s but %s found", expected, value)));
    }
}
