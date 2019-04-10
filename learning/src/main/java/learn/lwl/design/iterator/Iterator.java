package learn.lwl.design.iterator;

public interface Iterator {
    Boolean hasNext();

    Object next();

    Boolean hasPrevious();

    Object previous();

    void last();

    void first();
}
