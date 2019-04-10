package learn.lwl.design.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry {
    private String name;
    private List<Entry> directory = new ArrayList<>();
    private Entry parent;


    @Override
    public Entry getParent() {
        return parent;
    }

    @Override
    public void setParent(Entry entry) {
        this.parent = entry;
    }


    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(Entry entry) {
        entry.setParent(this);
        directory.add(entry);
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            size += iterator.next().getSize();
        }
        return size;
    }

    @Override
    public String getPath() {
        if (parent == null) {
            return "/" + name;
        } else {
            return parent.getPath() + "/" + name;
        }
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            iterator.next().printList(prefix + "/" + name);
        }
    }
}
