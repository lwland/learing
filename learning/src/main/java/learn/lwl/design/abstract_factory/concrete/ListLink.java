package learn.lwl.design.abstract_factory.concrete;

import learn.lwl.design.abstract_factory.Link;

public class ListLink extends Link {
    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHtml() {
        return "<li><a href=\"" + url + "\">" + caption + "</li>";
    }
}
