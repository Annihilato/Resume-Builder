// Placeholder for PdfExporter.java

import java.io.FileOutputStream;
import java.io.File;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Simple PDF exporter using iText 5.x API.
 *
 * NOTE: You must add an iText library JAR to the classpath (or use OpenPDF as an alternative).
 * Example Maven dependency (iText 5; check licensing for your use):
 *
 *    <dependency>
 *      <groupId>com.itextpdf</groupId>
 *      <artifactId>itextpdf</artifactId>
 *      <version>5.5.13.3</version>
 *    </dependency>
 *
 * Or download itextpdf-5.x.x.jar and put it on your classpath.
 */
public class Pdfexporter {
    public static void export(String plainTextResume, File outputPdf) throws Exception {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try (FileOutputStream fos = new FileOutputStream(outputPdf)) {
            PdfWriter.getInstance(document, fos);
            document.open();

            // Simple approach: write the plain text as paragraphs, preserving blank lines
            String[] lines = plainTextResume.split("\\r?\\n");
            StringBuilder paragraphBuffer = new StringBuilder();
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    if (paragraphBuffer.length() > 0) {
                        document.add(new Paragraph(paragraphBuffer.toString()));
                        document.add(new Paragraph(" ")); // empty line
                        paragraphBuffer.setLength(0);
                    } else {
                        document.add(new Paragraph(" "));
                    }
                } else {
                    if (paragraphBuffer.length() > 0) {
                        paragraphBuffer.append("\n").append(line);
                    } else {
                        paragraphBuffer.append(line);
                    }
                }
            }
            if (paragraphBuffer.length() > 0) {
                document.add(new Paragraph(paragraphBuffer.toString()));
            }
        } finally {
            if (document.isOpen()) document.close();
        }
    }
}
