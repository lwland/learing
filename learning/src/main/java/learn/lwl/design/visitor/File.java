package learn.lwl.design.visitor;

public class File extends Entry {
    private String name;
    private int size;
    private Entry parent;

    public Entry getParent() {
        return parent;
    }

    public void setParent(Entry parent) {
        this.parent = parent;
    }

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        parent = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
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
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
