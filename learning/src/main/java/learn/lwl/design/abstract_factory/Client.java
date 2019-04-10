package learn.lwl.design.abstract_factory;

import learn.lwl.design.abstract_factory.concrete.ListFactory;

public class Client {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory(ListFactory.class.getName());
        Link people = factory.createLink("人民日报", "http://www.people.com");
        Link gmw = factory.createLink("光明日报", "http://www.gmw.com");

        Link baidu = factory.createLink("百度", "http://www.baidu.com");

        Link yahooBj = factory.createLink("北京雅虎", "http://bj.yahoo.com");
        Link yahooHongkong = factory.createLink("香港雅虎", "http://hk.yahoo.com");
        Link google = factory.createLink("谷歌", "http://wwww.google.com");

        Tray tray = factory.createTray("日报");
        tray.add(people);
        tray.add(gmw);
        Tray tray3 = factory.createTray("雅虎");
        tray3.add(yahooBj);
        tray3.add(yahooHongkong);
        Tray tray1 = factory.createTray("搜索引擎");
        tray1.add(baidu);
        tray1.add(tray3);
        tray1.add(google);

        Page page = factory.createPage("linked", "lwl");
        page.add(tray);
        page.add(tray1);
        page.outPut();

    }
}
