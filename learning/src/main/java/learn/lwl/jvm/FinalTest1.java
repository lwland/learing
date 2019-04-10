package learn.lwl.jvm;

public class FinalTest1 {
    public static final int a = 6/3;

    static {
        System.out.println("init FinalTest1");
    }

    public static void main(String[] args) {
        System.out.println(FinalTest1.a);
    }
}
