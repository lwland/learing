package learn.lwl.design.prototype;

public interface Product extends Cloneable {
    public abstract void user(String str);

    public abstract Product createClone();
}
