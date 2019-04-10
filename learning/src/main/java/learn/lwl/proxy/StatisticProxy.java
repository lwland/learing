package learn.lwl.proxy;

public class StatisticProxy implements Subject {
    private Subject realSubject;

    public StatisticProxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void rent() {
        System.out.println("before rent");
        realSubject.rent();
        System.out.println("after rent");
    }

    @Override
    public void hello(String name) {
        System.out.println("before hello");
        realSubject.hello(name);
        System.out.println("after hello");
    }
}
