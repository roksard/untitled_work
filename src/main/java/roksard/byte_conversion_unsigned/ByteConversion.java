package roksard.byte_conversion_unsigned;

public class ByteConversion {
    public static void main(String[] args) {
        byte b = Byte.MIN_VALUE;
        while (true) {
            int unsigned = Byte.toUnsignedInt(b);
            String hex = String.format("%2s", Integer.toHexString(unsigned)).replace(" ", "0");
            System.out.printf("%d = %d = %s \n", b, unsigned, "0x"+hex);
            if (b < Byte.MAX_VALUE) {
                b++;
            } else {
                break;
            }
        }
    }
}
