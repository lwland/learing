package com.learning.captcha.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.learning.captcha.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {
    @RequestMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
    public void captcha(@RequestParam("type") String type,
                        HttpServletResponse response) {

        int codeSize = 4;
        String code = CaptchaUtil.generateCode(codeSize);
        System.out.println(code);
        response.setContentType("image/jpeg");
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try (OutputStream os = response.getOutputStream()) {
            CaptchaUtil.outputImage(500, 200, os, code, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private DefaultKaptcha captchaProducer = null;

    @RequestMapping(value = "/google")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    public String vertify() {
        return "";
    }
}
