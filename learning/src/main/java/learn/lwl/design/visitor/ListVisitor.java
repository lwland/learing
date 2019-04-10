package learn.lwl.design.visitor;


import java.util.Iterator;

public class ListVisitor extends Visitor {
    private String currentDir = "";

    @Override
    public void visitor(Directory directory) {
        System.out.println(currentDir + "/" + directory);
        String save = currentDir;
        currentDir = currentDir = "/" + directory.getName();
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(this);
        }
        currentDir = save;
    }

    @Override
    public void visitor(File file) {
        System.out.println(currentDir + "/" + file);
    }
}
