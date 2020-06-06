package server.business;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class ReportPdf implements ReportInterface{

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Report", catFont));
        document.add(preface);
        document.newPage();
    }

    private static void addContent(Document document,String report) throws DocumentException {
        Anchor anchor = new Anchor("", catFont);
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        catPart.add(new Paragraph(report));
        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @Override
    public void makeReport(String msg, File location) {
        String FILE = location.toString();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addTitlePage(document);
            addContent(document,msg);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
