package roksard.nio.interrupt_while_writing;

import roksard.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static roksard.util.Util.timestamp;

public class WriteFile implements Runnable {
    private String fileName;
    private Logger logger;
    private boolean useNio;
    public WriteFile(String name, String fileName, boolean useNio) {
        logger = Logger.getLogger(name);
        this.fileName = fileName;
        this.useNio = useNio;
    }

    @Override
    public void run() {
        File file = new File(fileName);
        logger.log("write to: ", file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file);
             WritableByteChannel channel = Channels.newChannel(fos);
        ) {
            Instant writeStart = Instant.now();
            if (useNio) {
                channel.write(ByteBuffer.wrap(timestamp().getBytes(StandardCharsets.UTF_8)));
                while (ChronoUnit.MILLIS.between(writeStart, Instant.now()) < 5000) {
                    byte[] arr = new byte[]{65, 66, 67, 68};
                    channel.write(ByteBuffer.wrap(arr));
                }
            } else {
                fos.write(timestamp().getBytes(StandardCharsets.UTF_8));
                while (ChronoUnit.MILLIS.between(writeStart, Instant.now()) < 5000) {
                    byte[] arr = new byte[]{65, 66, 67, 68};
                    fos.write(arr);
                }
            }
            logger.log(" write successful.");
        } catch (FileNotFoundException e) {
            logger.log(e);
        } catch (IOException e) {
            logger.log(e);
            logger.log("could not write file, deleting it..");
            if (file.delete()) {
                logger.log("  deleted successfully");
            } else {
                logger.log("  could not delete file (", file.getAbsolutePath(), ")");
            }
        }
    }
}
