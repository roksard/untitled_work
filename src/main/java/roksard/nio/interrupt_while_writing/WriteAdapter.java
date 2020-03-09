package roksard.nio.interrupt_while_writing;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class WriteAdapter {
    private OutputStream outputStream;
    private WritableByteChannel channel;
    public WriteAdapter(OutputStream os) {
        outputStream = os;
    }
    public WriteAdapter(WritableByteChannel ch) {
        channel = ch;
    }

    public void write (byte[] buffer) throws IOException {
        if (outputStream != null) {
            outputStream.write(buffer);
        } else if (channel != null) {
            channel.write(ByteBuffer.wrap(buffer));
        }
    }
}
