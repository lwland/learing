package learn.lwl.design.decorator;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Display d1 = new StringDisplay("hello world");
        d1.show();
        Display d2 = new SideBorder(d1, '*');
        d2.show();
        Display d5 = new UpdownBorder(d1, '-');
        d5.show();
        List<String> list = new ArrayList<>();
        list.add("早上好");
        list.add("中午好");
        list.add("晚上好");
        Display d6 = new MultlineStringDisplay(list, 5);
        d6.show();
        Display d3 = new FullBorder(d1);
        d3.show();
        Display d4 = new UpdownBorder(new SideBorder(
                new FullBorder(
                        new SideBorder(
                                new FullBorder(
                                        d6),
                                '*')),
                '/'), '1');
        d4.show();
    }
}
