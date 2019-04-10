package learn.lwl.current.thread;

public class VolatileDemo {
    private static VolatileDemo instance = null;

    public static VolatileDemo getInstance() {
        if (instance == null) {
            instance = new VolatileDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileDemo.getInstance();
    }
}
