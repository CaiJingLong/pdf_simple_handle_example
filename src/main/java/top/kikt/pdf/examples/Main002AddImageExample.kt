package top.kikt.pdf.examples


import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfContentByte
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.codec.PngImage
import top.kikt.pdf.utils.ExampleUtils

import java.io.FileInputStream

// 添加图片
object Main002AddImageExample {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExampleUtils.run("example2.pdf") { document, writer, _ ->
            document.newPage()
            val dc = writer.directContent
            val image = PngImage.getImage(FileInputStream("icon.png"))
            image.setAbsolutePosition(0f, 0f) // 这步是设置绝对坐标, 图片和文字最大的不同是, 这东西从左下角开始算
            dc.addImage(image, true)

            document.newPage()
            image.scalePercent(66f)
            dc.addImage(image, true)
        }
    }

}
