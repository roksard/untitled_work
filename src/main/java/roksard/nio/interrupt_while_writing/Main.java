package roksard.nio.interrupt_while_writing;

import roksard.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static roksard.util.Util.sleepn;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("main");
        logger.log("started");
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WriteFile("A", "target/testfile-NIO.txt", true));
        exec.execute(new WriteFile("B", "target/testfile-IO.txt", false)); //using old IO (FileOutputStream)
        exec.shutdown();
        sleepn(1);
        logger.log("trying to interrupt all tasks...(waiting 3 sec)");
        exec.shutdownNow();
        sleepn(3000);
        boolean allTerminated = exec.isTerminated();
        logger.log("exec.isTerminated(): " + allTerminated);
        if (!allTerminated) {
            logger.log("Not all tasks terminated properly. Force close application with code 1");
            System.exit(1);
        }
    }
}

/* Output:
18:07:23.689 --main--  started
18:07:23.855 --A--  write to:  target/testfile-NIO.txt
18:07:23.855 --B--  write to:  target/testfile-IO.txt
18:07:23.856 --main--  trying to interrupt all tasks...(waiting 3 sec)
18:07:23.863 --A--  java.nio.channels.ClosedByInterruptException
18:07:23.863 --A--  could not write file, deleting it..
18:07:23.865 --A--    deleted successfully
18:07:26.857 --main--  exec.isTerminated(): false //B is not finished and is not interrupted
18:07:26.857 --main--  Not all tasks terminated properly. Force close application with code 1

Process finished with exit code 1
 */