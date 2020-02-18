package roksard.exceptions;

import java.io.IOException;

public class Exceptions1 {
    public static void methodInner() throws IOException {
        throw new OutOfMemoryError();
    }
    public static void main(String[] args) {
        try {
            methodInner();
            System.out.println("success");
        } catch (Throwable e) {
            System.out.println("error");
            System.out.println("caught: " + e);
        }
    }
}
