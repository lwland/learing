package learn.lwl.design.visitor;

public abstract class Entry extends Element{
    public abstract Entry getParent();

    public abstract void setParent(Entry entry);

    public abstract String getName();

    public abstract int getSize();

    public abstract String getPath();

    public void add(Entry entry) {

    }

    public void printlnList() {
        printList("");
    }

    public abstract void printList(String prefix);

    @Override
    public String toString() {
        return getName() + ":" + getSize();
    }
}
