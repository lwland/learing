package learn.lwl.jvm;

class Parent2 {
    static int a = 4;

    static {
        System.out.println("init parent2");
    }
}
class Child2 extends Parent2 {
    static int b = 5;

    static {
        System.out.println("init child2");
    }
}

public class Test6{
    static {
        System.out.println("init Test6");
    }

    public static void main(String[] args) {
        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        try {
            classLoader.loadClass("learn.lwl.jvm.Child2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}