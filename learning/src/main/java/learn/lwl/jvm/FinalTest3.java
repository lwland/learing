package learn.lwl.jvm;

class Final3 {
    public static final int a = 6 / 3;
    static {
        System.out.println("init Final3");
    }
}
public class FinalTest3 {
    static {
        System.out.println("init FinalTest3");
    }
    public static void main(String[] args) {
        System.out.println(Final3.a);
    }
}
