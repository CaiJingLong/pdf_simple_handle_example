package top.kikt.pdf.examples

import com.itextpdf.text.Document
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfWriter
import top.kikt.pdf.utils.getRect
import top.kikt.pdf.utils.logger
import java.awt.geom.AffineTransform
import java.io.FileOutputStream

@Suppress("SameParameterValue")
/// 使用addTemplate 来剪裁pdf的示例
object Main005SplitPageExample2 {

    private const val srcPath = "Profiling_your_Flutter_Apps.pdf"
    private const val targetPath = "out/example5.pdf"

    @JvmStatic
    fun main(args: Array<String>) {
        clipEveryPage(srcPath, targetPath, width = 300f, height = 300f, x = 100f, y = 100f)
    }

    fun clipEveryPage(src: String, target: String, x: Float = 0f, y: Float = 0f, width: Float = 0f, height: Float = 0f) {
        val reader = PdfReader(src)
        val rect = reader.getRect()
        val document = Document(Rectangle(width, height))
        val writer = PdfWriter.getInstance(document, FileOutputStream(target))
        if (x >= rect.width) {
            logger.error("x 不能大于 宽度, x: {} , width: {}", x, rect.width)
            return
        }

        if (y >= rect.height) {
            logger.error("y 不能大于 高度, y: {} , height: {}", y, rect.height)
            return
        }

        if (width + x >= rect.width) {
            logger.error("剪裁宽度越界, x + width = {}, 原始宽度: {}", x + width, rect.width)
            return
        }

        if (height + y >= rect.height) {
            logger.error("剪裁高度越界,  y + height = {}, 原始高度: {}", y + height, rect.height)
            return
        }

        document.open()

        for (i in 1..reader.numberOfPages) {
            val page = writer.getImportedPage(reader, i)
            document.newPage()
            val dc = writer.directContent
            dc.addTemplate(page, AffineTransform().apply {
                translate(x.toDouble(), y.toDouble())
            })
        }


        document.close()
        writer.close()
    }
}
