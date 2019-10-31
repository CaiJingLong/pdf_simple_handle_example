package top.kikt.pdf.examples;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import top.kikt.pdf.utils.CommonUtils;
import top.kikt.pdf.utils.ExampleUtils;

import java.io.FileOutputStream;
import java.io.IOException;

/// 一个最简单的创建的Example
public class Main001CreateExample {

    public static void main(String[] args) throws Exception {
        ExampleUtils.INSTANCE.run("example1.pdf", new ExampleRunner() {
            public void invoke(Document document, PdfWriter writer, BaseFont baseFont) throws DocumentException {
                document.newPage();
                document.addAuthor("caijinglong");
                document.addCreationDate();
                document.addTitle("测试标题");
                document.addSubject("我是测试的副标题");

                Font titleFont = new Font(baseFont, 18, Font.NORMAL, BaseColor.BLACK);
                Font contentFont = new Font(baseFont, 12, Font.NORMAL);

                // 添加段落
                document.add(new Paragraph("我是中文", titleFont));
                document.add(new Paragraph("我是内容", contentFont));
            }
        });
    }

}
