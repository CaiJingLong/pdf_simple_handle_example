package top.kikt.pdf.examples

import com.itextpdf.text.Font
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import top.kikt.pdf.utils.ExampleUtils

// 表格相关的操作
object Main003InsertTableExample {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExampleUtils.run("example3.pdf") { document, _, baseFont ->
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
