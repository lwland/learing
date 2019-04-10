package learn.lwl.design.template;

public class StringDisPlay extends AbstractDisplay {
    private String str;

    public StringDisPlay(String str) {
        this.str = str;
    }

    @Override
    public void open() {
        System.out.println("+----+");
    }

    @Override
    public void close() {
        System.out.println("+----+");
    }

    @Override
    public void print() {
        System.out.println(str);
    }
}
