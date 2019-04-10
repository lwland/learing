package learn.lwl.jvm;

public class Client {
    public static void main(String[] args) {
        Work work=new Work();
        Car car=new Car();
        work.setCar(car);
        work.work();

    }
}
