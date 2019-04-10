package learn.lwl.design.template;

public abstract class AbstractDisplay {
    protected abstract void open();

    protected abstract void close();

    protected abstract void print();

    public final void display() {//final不希望子类对处理逻辑进行修改
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
