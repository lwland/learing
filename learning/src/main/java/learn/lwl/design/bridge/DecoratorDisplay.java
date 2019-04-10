package learn.lwl.design.bridge;

public class DecoratorDisplay extends DisplayImpl {
    private char begin;
    private char end;
    private char decorator;


    public DecoratorDisplay(char begin, char end, char decorator) {
        this.begin = begin;
        this.end = end;
        this.decorator = decorator;

    }

    @Override
    public void rawOpen() {
        System.out.print(begin);

    }

    @Override
    public void rawPrint() {
        System.out.print(decorator);
    }

    @Override
    public void rawClose() {
        System.out.println(end);

    }
}
