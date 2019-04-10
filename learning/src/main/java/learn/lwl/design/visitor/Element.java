package learn.lwl.design.visitor;

public abstract class Element {
    public abstract void accept(Visitor visitor);
}
