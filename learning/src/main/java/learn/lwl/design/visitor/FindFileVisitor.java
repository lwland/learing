package learn.lwl.design.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindFileVisitor extends Visitor {
    private String match;

    public FindFileVisitor(String match) {
        this.match = match;
    }

    private List<File> list = new ArrayList<>();

    @Override
    public void visitor(Directory directory) {
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(this);
        }
    }

    @Override
    public void visitor(File file) {
        String[] nameAndSuffix = file.getName().split("\\.");
        if (nameAndSuffix != null && nameAndSuffix.length == 2 && nameAndSuffix[1].equals(match) ) {
            list.add(file);
        }
    }

    public Iterator<File> iterator() {
        return list.iterator();
    }
}
