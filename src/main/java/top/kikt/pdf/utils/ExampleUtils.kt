package top.kikt.pdf.utils

import com.itextpdf.text.Document
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.kikt.pdf.examples.ExampleRunner

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

import org.apache.pdfbox.pdmodel.PDDocument

object ExampleUtils {

    private val logger = LoggerFactory.getLogger(ExampleUtils::class.java)

    init {

        File("out").mkdirs()
        logger.info("初始化完毕")
    }


    @Throws(Exception::class)
    fun run(outputPdfPath: String, runner: (document: Document, writer: PdfWriter, baseFont: BaseFont) -> Unit) {
        run(outputPdfPath, object : ExampleRunner {
            override fun invoke(document: Document, writer: PdfWriter, baseFont: BaseFont) {
                runner(document, writer, baseFont)
            }
        })
    }

    @Throws(Exception::class)
    fun run(outputPdfPath: String, runner: ExampleRunner) {
        logger.info("准备向 $outputPdfPath 中输出pdf")

        val start = System.currentTimeMillis()
        val document = Document()
        val realOutputPath = "out/$outputPdfPath"
        val fileOutputStream = FileOutputStream(realOutputPath)
        val writer = PdfWriter.getInstance(document, fileOutputStream)
        document.open()

        val baseFont = CommonUtils.baseFont

        try {
            runner.invoke(document, writer, baseFont)
            logger.info(outputPdfPath + " 有 " + writer.pageNumber + " 个页面")
            document.close()
        } catch (e: Exception) {
            logger.info("文档写入出错")
            File(realOutputPath).deleteOnExit()
            return
        }
        writer.close()
        fileOutputStream.close()

        val end = System.currentTimeMillis()
        val runtime = end - start
        logger.info("执行时间 : $runtime 毫秒")

        // convert
//        usePdfBoxConvertPage(realOutputPath)
    }

    @Throws(IOException::class)
    private fun usePdfBoxConvertPage(outputPdfPath: String) {
        val document = PDDocument.load(File(outputPdfPath))
        val file = File(outputPdfPath)
        val newPath = "${file.nameWithoutExtension}-new.pdf"
        document.save(newPath)

        val info = document.documentInformation
        info.producer = "PdfBox"
        document.documentInformation = info

        document.close()
    }

}
