package roksard.xlsx_workbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkBookCreator {
    static final String _resourceExt_ = ".xlsx";
    static final String _resourcePath_ = "target/classes/workbook1";

    public static List<Double> generateDoubles(int amount, int offset, int limit) {
        List<Double> doubles = new ArrayList<>(amount);
        Random rand = new Random();
        int pageSize = Math.min(amount-offset, limit);
        for(int i = 0; i < pageSize; i++) {
            doubles.add(rand.nextDouble());
        }
        return doubles;
    }

    public static void writeBook(String resourcePath,
                                 String resourceExt,
                                 String nameSuffix,
                                 int dataSize,
                                 int pageLimit,
                                 boolean useLowMemWorkbook,
                                 boolean printPageInfo) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(resourcePath + resourceExt);
             XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
             Workbook workbook = useLowMemWorkbook ? new SXSSFWorkbook(wb, 10000) : wb;
        ) {
            System.out.println("writeBook(" + nameSuffix + ", " + dataSize + ", " + pageLimit + ")");
            int offset = 0;
            List<Double> doubles;
            int rowId = 2;
            Sheet sheet = workbook.getSheetAt(0);
            do {
                doubles = generateDoubles(dataSize, offset, pageLimit);
                for (Double d : doubles) {
                    Row row = sheet.createRow(rowId++);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(d);
                }
                offset += pageLimit;
                if (printPageInfo) {
                    System.out.println(doubles.size() + "/" + dataSize);
                }
            } while (doubles.size() == pageLimit);
            doubles.clear();
            File file = new File(resourcePath + nameSuffix + resourceExt);
            if (file.exists()) {
                if (!file.delete()) {
                    System.out.println("could not delete file " + file.getAbsolutePath());
                }
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            System.out.println("finished writeBook(" + nameSuffix + ", " + dataSize + ", " + pageLimit + ")");
        }
    }


    public static void main(String[] args) throws Exception {
        final int size = 1000000;
        //writeBook(_resourcePath_, _resourceExt_, "-full", size, size, false); //should produce OutOfMemory error
        //writeBook(_resourcePath_, _resourceExt_, "-page", size, size / 100, false); //paged, oldbook
        writeBook(_resourcePath_, _resourceExt_, "-page", size, size, true, false); //nopage, newbook
    }
}
