package roksard.io_java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteArrayOutputStreamTest {
    public static void main(String[] args) throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ByteArrayOutputStream bos2 = new ByteArrayOutputStream()
        ) {
            System.out.println(bos.getClass());
            System.out.println(bos2.getClass());
        }
    }
    public static void main1(String[] args) throws IOException {
        String s = "abc";
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(10)) {
            bos.write(s.getBytes(Charset.defaultCharset()));
            System.out.println(Arrays.toString(bos.toByteArray()));
            System.out.println(bos.toString(Charset.defaultCharset().name()));
        }
    }
}
