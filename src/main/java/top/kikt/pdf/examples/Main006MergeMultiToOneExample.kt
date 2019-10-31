package top.kikt.pdf.examples

import com.itextpdf.awt.geom.AffineTransform
import com.itextpdf.text.Document
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfWriter
import top.kikt.pdf.utils.getRect
import java.io.FileOutputStream

/// 将单数页和双数页纵向合并为一页
object Main006MergeMultiToOneExample {

    private const val src = "Profiling_your_Flutter_Apps.pdf"
    private const val target = "out/example6.pdf"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = PdfReader(src)
        val rect = reader.getRect()
        val document = Document(Rectangle(rect.width, rect.height * 2))
        val writer = PdfWriter.getInstance(document, FileOutputStream(target))

        document.open()
        for (i in 1..reader.numberOfPages) {
            val page = writer.getImportedPage(reader, i)

            val newPage = i % 2 == 1
            if (newPage) {
                document.newPage()
            }

            val dc = writer.directContent

            val y = if (newPage) rect.height.toDouble() else 0.0

            dc.addTemplate(page, AffineTransform().apply {
                translate(0.0, y)
            })
        }

        document.close()
        writer.close()
        reader.close()
    }

}