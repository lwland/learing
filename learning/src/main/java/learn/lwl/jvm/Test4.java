package learn.lwl.jvm;

class Parent {
    static int a = 4;
    static {
        System.out.println("init parent");
    }
}
class Child extends Parent {
    static int b = 5;

    static {
        System.out.println("init child");
    }
}
public class Test4 {
    static {
        System.out.println("init Test4");
    }
    public static void main(String[] args) {
        System.out.println(Child.a);
    }
}