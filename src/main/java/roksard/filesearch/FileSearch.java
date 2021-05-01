package roksard.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileSearch {
    Logger logger = LoggerFactory.getLogger(FileSearch.class);
    ExecutorService executor;

    public static void main(String[] args) {
        Result result = new Result();
        new FileSearch().searchBySubstring("F:\\xfiles\\games doc\\", true, "tank", result);
        System.out.println(result);

    }

    public Future<Result> searchBySubstringAsync(String directory, boolean searchSubDirs, String subString, Result result) {
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        return executor.submit(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                searchBySubstring(directory, searchSubDirs, subString, result);
                return result;
            }
        });
    }

    public void searchBySubstring(String directory, boolean searchSubDirs, String subString, Result result) {
        File dir = new File(directory);
        processDir(dir, searchSubDirs, subString, result);
    }

    public void processDir(File directory, boolean searchSubDirs, String subString, Result result) {
        logger.debug("directory={}", directory.getAbsolutePath());
        File[] files = directory.listFiles();
        List<File> directoryList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                if (searchSubDirs && !file.getName().equals(".") && !file.getName().equals("..")) {
                    directoryList.add(file);
                }
            } else {
                if (file.getName().endsWith(".txt")) {
                    fileSearchSubstring(file, subString, result);
                }
            }
        }
        for (File subDirectory : directoryList) {
            processDir(subDirectory, searchSubDirs, subString, result);
        }
    }

    public void fileSearchSubstring(File file, String subString, Result result) {
        String subStringLow = subString.toLowerCase();
        logger.debug("file={}", file.getAbsolutePath());
        final int lineLimit = 1_000_000;
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < lineLimit; i++) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(subStringLow)) {
                    result.add(String.format("%s: %d", file.getName(), i+1));
                }
            }
            logger.warn("File is too large, line limit exceeded. File {}", file.getAbsolutePath());
        } catch (NoSuchElementException e) {
            //eof
//            logger.debug("EOF");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
