package com.learning.captcha.util;

import com.learning.captcha.model.ImgFontByte;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

@Slf4j
public class CaptchaUtil {
    //存放code的key
    private static final String RADNOM_CODE_KEY = "RANDOM_CODE_KEY";
    //验证码的取值
    private static final String CAPTCHA_SOURCE = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
    //字体类型
    private static final String[] FONT_NAMES = {"Algerian", "Arial", "Arial Black", "Agency FB", "Calibri", "Cambria", "Gadugi", "Georgia", "Consolas", "Comic Sans MS", "Courier New",
            "Gill sans", "Time News Roman", "Tahoma", "Quantzite", "Verdana"
    };
    //字体样式
    private static final int[] FONT_TYLES = {Font.BOLD, Font.ITALIC, Font.ROMAN_BASELINE, Font.PLAIN, Font.BOLD + Font.ITALIC
    };
    //字体颜色
    private static final Color[] COLOR_TYPES = {Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW, Color.GREEN, Color.BLUE,
            Color.DARK_GRAY, Color.BLACK, Color.RED
    };

    private static final Font baseFont = null;

    static {

    }

    public static String generateCode(int codeSize) {
        return generateCode(codeSize, CAPTCHA_SOURCE);
    }

    private static String generateCode(int codeSize, String source) {
        if (source == null || source.length() == 0) {
            source = CAPTCHA_SOURCE;
        }
        int sourceLength = source.length();
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < codeSize; i++) {
            buffer.append(source.charAt(random.nextInt(sourceLength)));
        }
        return buffer.toString();
    }

    public static void outputImage(int width, int height, OutputStream os, String code, String type) throws IOException {
        int codeLength = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpace = COLOR_TYPES;
        float[] fraction = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpace[random.nextInt(colorSpace.length)];
            fraction[i] = random.nextFloat();
        }
        Arrays.sort(fraction);
        graphics2D.setColor(Color.GRAY);//设置边框色
        graphics2D.fillRect(0, 0, width, height);

        Color c = getRandColor(200, 250);
        graphics2D.setColor(c);// 设置背景色
        graphics2D.fillRect(0, 2, width, height - 4);
        char[] chars = code.toCharArray();
        //1 绘制干扰线
        graphics2D.setColor(getRandColor(160, 200));// 设置线条的颜色
        int lineNumbers = 20;
        if (type.equals("login") || type.contains("mix") || type.contains("3D")) {
            lineNumbers = 20;
        } else if (type.equals("coupons")) {
            lineNumbers = getRandomDrawLine();
        } else {
            lineNumbers = getRandomDrawLine();
        }
        for (int i = 0; i < lineNumbers; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics2D.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        // 2.添加噪点
        float yawpRate = 0.05f;
        if (type.equals("login") || type.contains("mix") || type.contains("3D")) {
            yawpRate = 0.05f; // 噪声率
        } else if (type.equals("coupons")) {
            yawpRate = getRandomDrawPoint(); // 噪声率
        } else {
            yawpRate = getRandomDrawPoint(); // 噪声率
        }
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
        //3、使图片扭曲
        shear(graphics2D, width, height, c);

        Double rd = random.nextDouble();
        Boolean rb = random.nextBoolean();


        if (type.contains("GIF") || type.contains("mixGIF")) {
            GifEncoder gifEncoder = new GifEncoder(); // gif编码类，这个利用了洋人写的编码类
            // 生成字符
            gifEncoder.start(os);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(150);
            gifEncoder.setRepeat(0);
            AlphaComposite ac3;
            for (int i = 0; i < codeLength; i++) {
                graphics2D.setColor(getRandColor(100, 160));
                graphics2D.setFont(getRandomFont(height, type));
                for (int j = 0; j < codeLength; j++) {
                    AffineTransform affine = new AffineTransform();
                    affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (width / codeLength) * i + (height - 4) / 2, height / 2);
                    graphics2D.setTransform(affine);
                    graphics2D.drawChars(chars, i, 1, ((width - 10) / codeLength) * i + 5, height / 2 + (height - 4) / 2 - 10);

                    ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(j, i, codeLength));
                    graphics2D.setComposite(ac3);
                    graphics2D.drawOval(random.nextInt(width), random.nextInt(height), 5 + random.nextInt(10), 5 + random.nextInt(10));
                    gifEncoder.addFrame(image);
                    image.flush();
                }
            }
            gifEncoder.finish();
            graphics2D.dispose();
        } else {
            for (int i = 0; i < codeLength; i++) {
                graphics2D.setColor(getRandColor(100, 160));
                graphics2D.setFont(getRandomFont(height, type));

                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (width / codeLength) * i + (height - 4) / 2, height / 2);
                graphics2D.setTransform(affine);
                graphics2D.drawOval(random.nextInt(width), random.nextInt(height), 5 + random.nextInt(10), 5 + random.nextInt(10));
                graphics2D.drawChars(chars, i, 1, ((width - 10) / codeLength) * i + 5, height / 2 + (height - 4) / 2 - 10);
            }

            graphics2D.dispose();
            ImageIO.write(image, "jpg", os);
        }


    }

    //获取随机字体
    private static Font getRandomFont(int heigh, String type) {
        Random random = new Random();
        String name = FONT_NAMES[random.nextInt(FONT_NAMES.length)];
        int style = FONT_TYLES[random.nextInt(FONT_TYLES.length)];
        int fontSize = getRandomFontSize(heigh);
        if ("login".equals(type)) {
            return new Font(name, style, fontSize);
        } else if ("coupons".equals(type)) {
            return new Font(name, style, fontSize);
        } else if (type.contains("3D")) {
            return new ImgFontByte(baseFont).getFont(fontSize, style);
        } else if (type.contains("mix")) {
            int flag = random.nextInt(10);
            if (flag > 4) {
                return new Font(name, style, fontSize);
            } else {
                return new ImgFontByte(baseFont).getFont(fontSize, style);
            }
        } else {
            return new Font(name, style, fontSize);


        }
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        Random random = new Random();
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }


    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);

    }

    //干扰线范围随机数
    private static int getRandomDrawLine() {
        int min = 20;
        int max = 155;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    //噪点数
    private static float getRandomDrawPoint() {
        float min = 0.05f;
        float max = 0.15f;
        return min + ((max - min) * new Random().nextFloat());
    }

    //随机字体大小
    private static int getRandomFontSize(int h) {
        int min = h - 8;
        Random random = new Random();
        return random.nextInt(11) + min;
    }
    //偏移量
    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }
    //透明度
    private static float getAlpha(int i, int j, int verifySize) {
        int num = i + j;
        float r = (float) 1 / verifySize, s = (verifySize + 1) * r;
        return num > verifySize ? (num * r - s) : num * r;
    }

}
