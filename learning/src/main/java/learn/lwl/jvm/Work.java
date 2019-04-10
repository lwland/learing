package learn.lwl.jvm;

public class Work {
    public static int a=1;
    public static int b;
    static {
        b=1;
    }
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void work() {
        car.run();
    }
}
