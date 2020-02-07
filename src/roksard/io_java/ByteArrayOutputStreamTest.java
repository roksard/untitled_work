package roksard.io_java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteArrayOutputStreamTest {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(10);
        String s = "abc";
        bos.write(s.getBytes(Charset.defaultCharset()));
        System.out.println(Arrays.toString(bos.toByteArray()));
        System.out.println(bos.toString(Charset.defaultCharset().name()));
    }
}
