package learn.lwl.design.visitor;

public abstract class Visitor {
    public abstract void visitor(Directory directory);

    public abstract void visitor(File file);
}
