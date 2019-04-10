package learn.lwl.design.abstract_factory.concrete;

import learn.lwl.design.abstract_factory.Item;
import learn.lwl.design.abstract_factory.Page;

import java.util.Iterator;


public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHtml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><title>" + title + "</title></head><body>\n");
        buffer.append("<ul>\n");
        Iterator<Item> itemIterator = content.iterator();
        while (itemIterator.hasNext()) {
            buffer.append(itemIterator.next().makeHtml());
        }
        buffer.append("</ul>");
        buffer.append("<hr><address>" + author + "</address></hr>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
