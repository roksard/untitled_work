package roksard.byte_conversion_unsigned;

import roksard.util.Format;

public class ByteConversion {
    public static void main(String[] args) {
        byte b = Byte.MIN_VALUE;
        while (true) {
            int unsigned = Byte.toUnsignedInt(b);
            String hex = Format.byteAsHex(b);
            System.out.printf("%d = %d = %s \n", b, unsigned, "0x"+hex);
            if (b < Byte.MAX_VALUE) {
                b++;
            } else {
                break;
            }
        }
    }
}
