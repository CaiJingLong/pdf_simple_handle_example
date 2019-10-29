package top.kikt.pdf.examples

import com.itextpdf.text.pdf.PdfReader

object Main005SplitPageExample2 {

    const val target = "out/example5.pdf"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = PdfReader("out/example3.pdf")

    }

}