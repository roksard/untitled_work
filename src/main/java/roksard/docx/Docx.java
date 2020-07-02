package roksard.docx;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class Docx {
    public static void main(String[] args) throws IOException {
        try (   InputStream fis = new FileInputStream("target/docin2.docx");
                XWPFDocument doc = new XWPFDocument(fis)
        ) {
            XWPFParagraph paragraph = doc.createParagraph();
            int runc = 0;
            XWPFRun runBr = paragraph.insertNewRun(runc++);
            runBr.addBreak(BreakType.PAGE);
            XWPFRun runContent = paragraph.insertNewRun(runc++);
            runContent.setFontFamily("Times New Roman");
            runContent.setFontSize(12);
            runContent.setText("Проверка введенного очень длинного текста. Должно быть добавлено в самом конце документа на новой странице.");


            try (FileOutputStream out = new FileOutputStream("target/docout.docx")) {
                doc.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
