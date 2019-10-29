package top.kikt.pdf.examples

import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.kikt.pdf.utils.ExampleUtils

object Main003InsertTableExample {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExampleUtils.run("example3.pdf") { document, writer, baseFont ->
            document.newPage()
            val table = PdfPTable(5)
            val font = Font(baseFont, 12f, Font.NORMAL)

            for (i in 0 until table.numberOfColumns) {
                val cell = PdfPCell()
                cell.addElement(Paragraph("第 1 行 $i 列", font))
                table.addCell(cell)
            }

            for (i in 0 until table.numberOfColumns - 1) {
                val cell = PdfPCell()
                cell.addElement(Paragraph("第 2 行 $i 列", font))
                table.addCell(cell)
            }

            // 这里需要注意, 虽然添加table到document总是成功的, 但如果你的行数据没有填充完成, 则当行不会被添加到文档中
            table.completeRow()

            // 另, 如果table中无任何添加成功的数据, 则并不会被实际添加到 pdf 中

            document.add(table)
        }
    }
}
