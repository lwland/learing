package learn.lwl.design.bridge;

public class StringDisplayImpl extends DisplayImpl {
    private String str;
    private Integer width;

    public StringDisplayImpl(String str) {
        this.str = str;
        this.width = str.getBytes().length;
    }

    @Override
    public void rawOpen() {
        printLine();

    }

    @Override
    public void rawPrint() {
        System.out.println("|" + str + "|");

    }

    @Override
    public void rawClose() {
        printLine();

    }

    public void printLine() {
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");

    }
}
