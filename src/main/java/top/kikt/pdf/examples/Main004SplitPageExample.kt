package top.kikt.pdf.examples

import com.itextpdf.text.pdf.PdfArray
import com.itextpdf.text.pdf.PdfName
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfStamper
import top.kikt.pdf.utils.getFloat
import top.kikt.pdf.utils.getMediaBox
import java.io.File
import java.util.*


// 页面分割 很接近官方提供的example, 但是这东西比较难以理解, 需要对 pdf command 有很多的了解
// 后面会添加一个不被官方推荐, 但是更加容易理解的Example, 应该为005 , 使用 AddTemplate 的方式
object Main004SplitPageExample {

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = PdfReader("out/example3.pdf")
        val stamper = PdfStamper(reader, File("out/example4.pdf").outputStream())

        val pageN = reader.getPageN(1)
        val mediaBoxDict = pageN.getMediaBox()
        val x = mediaBoxDict.getFloat(0) + 100
        val y = mediaBoxDict.getFloat(1) + 200
        val width = mediaBoxDict.getFloat(2) - 200
        val height = mediaBoxDict.getFloat(3)

        val command = String.format(Locale.ROOT,
                "\nq %.2f %.2f %.2f %.2f re W n\nq\n",
                x, y, width, height)

        stamper.getUnderContent(1).setLiteral(command);
        stamper.getOverContent(1).setLiteral("\nQ\nQ\n");

        stamper.close()
    }

}