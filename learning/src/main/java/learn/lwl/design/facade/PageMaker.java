package learn.lwl.design.facade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PageMaker {
    public PageMaker() {
    }

    public static void makeWelcomePage(String mailAddr, String fileName,String out) throws IOException {
        Properties properties = DataBase.getProperties(fileName);
        String userName = properties.getProperty("name");
        HtmlWriter writer = new HtmlWriter(new FileWriter(out));
        writer.title("welcome to" + userName + "'s page");
        writer.paragraph(userName + "欢迎来到你的首页");
        writer.paragraph("等着你的邮件");
        writer.mailTo(mailAddr, userName);
        writer.close();
    }
}
