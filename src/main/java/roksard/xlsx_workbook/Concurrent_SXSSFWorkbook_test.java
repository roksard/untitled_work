package roksard.xlsx_workbook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrent_SXSSFWorkbook_test {
    public static void main(String[] args) {
        final String path = WorkBookCreator._resourcePath_;
        final String ext = WorkBookCreator._resourceExt_;
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i ++) {
            final String suff = "-" + i;
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        WorkBookCreator.writeBook(path, ext, suff, 100000, 1000, true, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(5);
                    }
                }
            });
        }
        exec.shutdown();
    }
}
