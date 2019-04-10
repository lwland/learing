package learn.lwl.design.visitor;


import java.util.Iterator;

public class SizeVisitor extends Visitor {
    private int size = 0;

    @Override
    public void visitor(Directory directory) {
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            size += iterator.next().getSize();
        }
    }

    @Override
    public void visitor(File file) {
        size += file.getSize();
    }

    public int getSize() {
        return size;
    }
}
