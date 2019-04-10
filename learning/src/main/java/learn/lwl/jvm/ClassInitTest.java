package learn.lwl.jvm;

public class ClassInitTest {
    public static int a;
    public static int b = 0;
    private static ClassInitTest instance = new ClassInitTest();
    private ClassInitTest() {
        a++;
        b++;
    }

    public static ClassInitTest getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        ClassInitTest init = ClassInitTest.getInstance();
        System.out.println("a=" + init.a);
        System.out.println("b=" + init.b);
    }
}
