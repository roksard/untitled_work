package roksard.pdf.apache_pdfbox;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

import java.io.File;
import java.io.IOException;

public class AddTextPageToEnd {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println( "Usage: java " + AddTextPageToEnd.class.getName() + " <input-pdf> <output-pdf>" );
        } else {
            try (PDDocument document = PDDocument.load(new File(args[0]))) {
                if (document.isEncrypted()) {
                    throw new IOException("Encrypted documents are not supported for this example");
                }
                if (document.getNumberOfPages() <= 1) {
                    throw new IOException("Error: A PDF document must have at least one page!");
                }

                PDPage page = new PDPage(document.getPage(0).getBBox());
                try (PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {

                    PDFont font = PDType0Font.load(document, new File(AddTextPageToEnd.class.getClassLoader().getResource("times.ttf").getFile()));
                    cs.setFont(font, 12);
                    int fieldLeft = 50;
                    int fieldTop = 40;
                    int rowHeight = 10;
                    cs.beginText();
                    {
                        cs.newLineAtOffset(fieldLeft, page.getBBox().getHeight()-fieldTop);
                        cs.showText("Пример текста для отображения в этом PDF документе. Эта страница была добавлена в конце документа. Перенос?");
                        cs.newLineAtOffset(0, -rowHeight);
                        cs.showText("Этот текст должен отображаться на новой строке");
                    }
                    cs.endText();
                }


                document.addPage(page);
                document.save(args[1]);
            }
        }
    }
}
