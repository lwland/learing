package learn.lwl.jvm;

class Parent1 {
    static int a = 4;

    static {
        System.out.println("init parent1");
    }
}
class Child1 extends Parent1 {
    static int b = 5;

    static {
        System.out.println("init child1");
    }
}
public class Test5 {
    static {
        System.out.println("init Test5");
    }

    public static void main(String[] args) {
        System.out.println(Parent1.a);
        System.out.println(Child1.a);
        System.out.println(Child1.b);
    }
}