package roksard.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WriteFile implements Runnable {

    @Override
    public void run() {
        File file = new File("target/testfile.txt");
        System.out.println(NioFile.timestamp() + " write to: " + file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file);
             WritableByteChannel out = Channels.newChannel(fos);
        ) {
            out.write(ByteBuffer.wrap(NioFile.timestamp().getBytes(StandardCharsets.UTF_8)));
            int i = 0;
            while (i < 1000) {
                byte[] arr = new byte[] {65,66,67,68,(byte)i++};
                out.write(ByteBuffer.wrap(arr));
            }
            System.out.println(NioFile.timestamp() + " write successful.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not write file, deleting it..");
            if (file.delete()) {
                System.out.println("  deleted successfully");
            } else {
                System.out.println("  could not delete file (" + file.getAbsolutePath() +  ")");
            }
        }
    }
}

public class NioFile {
    static void sleepn(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String timestamp() {
        Instant time = Instant.now();
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(time,
                ZoneId.of("UTC+5")));
    }

    static void interrupt(ExecutorService exec) {
        new Thread() {
            @Override
            public void run() {
                sleepn(1);
                System.out.println(timestamp() + " interrupting...");
                exec.shutdownNow();
                sleepn(1000);

                System.out.println(timestamp() + " exiting...");
                System.exit(0);
            }
        }.start();
    }

    public static void main(String[] args) {
        System.out.println("started");
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WriteFile());
        exec.shutdown();
        //interrupt(exec);

    }
}
