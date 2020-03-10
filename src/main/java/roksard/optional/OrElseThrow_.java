package roksard.optional;

import java.util.Optional;

public class OrElseThrow_ {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable("abc");
        String value = opt.orElseThrow(() -> new RuntimeException("not found"));
        System.out.println("value = " + value);

        opt = Optional.ofNullable(null);
        String value2 = opt.orElseThrow(() -> new RuntimeException("not found"));
        System.out.println("value2 = " + value2);
    }
}
