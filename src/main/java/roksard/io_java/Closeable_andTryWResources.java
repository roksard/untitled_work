package roksard.io_java;

import java.io.Closeable;
import java.io.IOException;

class CC implements Closeable {
    String name;

    public CC(String name) {
        this.name = name;
    }

    @Override
    public void close() throws IOException {
        System.out.println(name + " closed");
    }
}

public class Closeable_andTryWResources {
    static void testMethod(CC obj) {
        try (CC x = new CC("x"); CC obj2 = obj;) {
            System.out.println("testMethod");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CC a = new CC("a");
        testMethod(a);
    }

    public static void main2(String[] args) {
        try (CC a = new CC("a");
             CC b = new CC("b");
        ) {
            System.out.println("working..");
            System.out.println(a);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
