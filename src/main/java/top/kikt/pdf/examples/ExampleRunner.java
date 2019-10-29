package top.kikt.pdf.examples;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;

public interface ExampleRunner {
    void invoke(Document document, PdfWriter writer, BaseFont baseFont) throws Exception;
}
