package top.kikt.pdf.utils

import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfArray
import com.itextpdf.text.pdf.PdfDictionary
import com.itextpdf.text.pdf.PdfName
import com.itextpdf.text.pdf.PdfReader
import top.kikt.pdf.entity.Size

fun PdfArray.getFloat(index: Int) = getAsNumber(index).floatValue()

fun PdfDictionary.getMediaBox(): PdfArray = getAsArray(PdfName.MEDIABOX)

fun PdfReader.getFirstPageSize(): Size {
    val box = getPageN(1).getMediaBox()
    return Size(box.getFloat(2), box.getFloat(3))
}

fun PdfReader.getRect(): Rectangle {
    val pageN = getPageN(1)
    val mediaBox = pageN.getMediaBox()
    return Rectangle(mediaBox.getFloat(0), mediaBox.getFloat(1), mediaBox.getFloat(2), mediaBox.getFloat(3))
}
