package learn.lwl.design.template;

public class CharDisPlay extends AbstractDisplay {
    private char aChar;

    public CharDisPlay(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public void open() {
        System.out.print("<<<<<");
    }

    @Override
    public void close() {
     System.out.print(">>>>");
    }

    @Override
    public void print() {
        System.out.print(aChar);

    }
}
