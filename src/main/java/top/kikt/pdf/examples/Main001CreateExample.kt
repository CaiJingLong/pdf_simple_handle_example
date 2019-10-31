package top.kikt.pdf.examples

import com.itextpdf.text.BaseColor
import com.itextpdf.text.Font
import com.itextpdf.text.Paragraph
import top.kikt.pdf.utils.ExampleUtils

/// 一个最简单的创建的Example
object Main001CreateExample {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExampleUtils.run("example1.pdf") { document, _, baseFont ->
            document.newPage()
            document.addAuthor("caijinglong")
            document.addCreationDate()
            document.addTitle("测试标题")
            document.addSubject("我是测试的副标题")

            val titleFont = Font(baseFont, 18f, Font.NORMAL, BaseColor.BLACK)
            val contentFont = Font(baseFont, 12f, Font.NORMAL)

            // 添加段落
            document.add(Paragraph("我是中文", titleFont))
            document.add(Paragraph("我是内容", contentFont))
        }
    }

}
