package learn.lwl.design.abstract_factory.concrete;

import learn.lwl.design.abstract_factory.Item;
import learn.lwl.design.abstract_factory.Tray;

import java.util.Iterator;

public class ListTray extends Tray {
    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHtml() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li>\n");
        stringBuffer.append(caption);
        stringBuffer.append("<ul>\n");
        Iterator<Item> itemIterator = trays.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            stringBuffer.append(item.makeHtml());
        }
        stringBuffer.append("</ul>\n");
        stringBuffer.append("</li>\n");
        return stringBuffer.toString();
    }
}
