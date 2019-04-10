package learn.lwl.jvm;

import java.util.Random;

class Final2 {
    public static final int a = new Random().nextInt(100);

    static {
        System.out.println("init FinalTest2");
    }
}
public class FinalTest2 {

    public static void main(String[] args) {
        System.out.println(Final2.a);
    }
}
