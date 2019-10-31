package top.kikt.pdf.examples

import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter

import java.io.FileNotFoundException

interface ExampleRunner {
    @Throws(Exception::class)
    operator fun invoke(document: Document, writer: PdfWriter, baseFont: BaseFont)
}
