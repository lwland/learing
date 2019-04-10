package learn.lwl.design.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementArrayList extends Element {
    private List<Entry> list = new ArrayList<>();

    public void add(Entry entry) {
        list.add(entry);
    }

    @Override
    public void accept(Visitor visitor) {
        Iterator<Entry> iterator = list.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            entry.accept(visitor);
        }
    }
}
