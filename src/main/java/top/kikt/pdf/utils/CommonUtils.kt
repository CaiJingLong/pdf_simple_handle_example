package top.kikt.pdf.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;

public class CommonUtils {

    private static final String fontPath = "STHeitiTC-Light-01.ttf";
    private static BaseFont baseFont;

    static {
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BaseFont getBaseFont() {
        return baseFont;
    }

}
