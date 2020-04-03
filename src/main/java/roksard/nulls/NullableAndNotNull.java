package roksard.nulls;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class NullableAndNotNull {
    static void methodA(@Nullable String x) {
        System.out.println(x);
    }
    static void methodB(@NotNull String x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        methodA("stringA");
        methodB("stringB");

        methodA(null);
        methodB(null);
    }
}
