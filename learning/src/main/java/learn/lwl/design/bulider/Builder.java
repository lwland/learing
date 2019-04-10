package learn.lwl.design.bulider;

public abstract class Builder {
    boolean init = false;

    public void buildString(String s) {
        if (init) {
            makeString(s);
        }
    }

    public void buildTitle(String s) {
        if (!init) {
            makeTitle(s);
            init = true;
        }
    }

    public void buildItems(String[] s) {
        if (init) {
            makeItems(s);
        }
    }

    public void buildDone() {
        if (init) {
            close();
        }
    }

    protected abstract void makeTitle(String s);

    protected abstract void makeString(String s);

    protected abstract void makeItems(String[] items);

    protected abstract void close();
}
