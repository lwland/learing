package learn.lwl.proxy;

//具体主题角色，提供业务逻辑的具体实现
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent a house");
    }

    @Override
    public void hello(String name) {
        System.out.println("hello " + name + " how a good day!!!");
    }
}
