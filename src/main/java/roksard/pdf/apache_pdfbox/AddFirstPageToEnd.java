package roksard.pdf.apache_pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class AddFirstPageToEnd {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println( "Usage: java " + AddFirstPageToEnd.class.getName() + " <input-pdf> <output-pdf>" );
        } else {
            try (PDDocument document = PDDocument.load(new File(args[0]))) {
                if (document.isEncrypted()) {
                    throw new IOException("Encrypted documents are not supported for this example");
                }
                if (document.getNumberOfPages() <= 1) {
                    throw new IOException("Error: A PDF document must have at least one page!");
                }
                document.addPage(document.getPage(0));
                document.save(args[1]);
            }
        }
    }
}
