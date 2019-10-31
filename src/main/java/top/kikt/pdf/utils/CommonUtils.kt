package top.kikt.pdf.utils

import com.itextpdf.text.DocumentException
import com.itextpdf.text.pdf.BaseFont

import java.io.IOException

object CommonUtils {

    private const val fontPath = "STHeitiTC-Light-01.ttf"
    lateinit var baseFont: BaseFont
        private set

    init {
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED)
        } catch (e: DocumentException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}
