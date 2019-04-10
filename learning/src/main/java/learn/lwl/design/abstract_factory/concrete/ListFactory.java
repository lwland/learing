package learn.lwl.design.abstract_factory.concrete;

import learn.lwl.design.abstract_factory.Factory;
import learn.lwl.design.abstract_factory.Link;
import learn.lwl.design.abstract_factory.Page;
import learn.lwl.design.abstract_factory.Tray;

public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
