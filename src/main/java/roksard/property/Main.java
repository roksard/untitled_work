package roksard.property;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Main {
    static void read(String fileName) {
        Properties p = new Properties();
        try {
            try (FileInputStream fis = new FileInputStream(fileName)) {
                p.loadFromXML(fis);
            }
            System.out.println("read success: " + p);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    static void write(String fileName) {
        try {
            Properties p = new Properties();
            p.setProperty("message", "Hello!");
            try (FileOutputStream fos = new FileOutputStream("roksard.property.xml")) {
                p.storeToXML(fos, "comment provided");
            }
            System.out.println("write success");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {
        String fileName = "roksard.property.xml";
        write(fileName);
        read(fileName);
    }
}
