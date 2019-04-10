package com.learning.captcha.model;

import java.awt.*;

public class ImgFontByte {
    private Font baseFont;

    public ImgFontByte(Font baseFont) {
        this.baseFont = baseFont;
    }

    public Font getFont(int fontSize, int fontStype) {
        return new Font("Arial", fontStype, fontSize);
    }

    private byte[] hex2byte(String str) {
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }
}

