package roksard.util;

public class Format {
    public static String byteAsHex(byte b) {
        int unsigned = Byte.toUnsignedInt(b);
        return String.format("%2s", Integer.toHexString(unsigned)).replace(" ", "0");
    }
}
