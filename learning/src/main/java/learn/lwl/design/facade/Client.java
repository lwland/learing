package learn.lwl.design.facade;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            PageMaker.makeWelcomePage("862876325@qq.com", "out", "welcome.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
