package top.kikt.pdf.examples;


import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;
import top.kikt.pdf.utils.ExampleUtils;

import java.io.FileInputStream;

// 添加图片
public class Main002AddImageExample {

    public static void main(String[] args) throws Exception {
        ExampleUtils.INSTANCE.run("example2.pdf", new ExampleRunner() {
            public void invoke(Document document, PdfWriter writer, BaseFont baseFont) throws Exception {
                document.newPage();
                PdfContentByte dc = writer.getDirectContent();
                Image image = PngImage.getImage(new FileInputStream("icon.png"));
                image.setAbsolutePosition(0, 0); // 这步是设置绝对坐标, 图片和文字最大的不同是, 这东西从左下角开始算
                dc.addImage(image, true);

                document.newPage();
                image.scalePercent(66f);
                dc.addImage(image, true);
            }
        });
    }

}
