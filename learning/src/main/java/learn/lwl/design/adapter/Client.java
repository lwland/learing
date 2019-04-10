package learn.lwl.design.adapter;

public class Client {
    public static void main(String[] args) {
        Print p=new PrintBanner("hello");
        p.printStrong();
        p.printWeek();
        Banner banner=new Banner("hello2");
        Print p2=new PrintBanner2(banner);
        p2.printStrong();
        p2.printWeek();
    }
}
