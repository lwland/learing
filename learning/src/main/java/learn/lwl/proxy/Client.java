package learn.lwl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Subject real = new RealSubject();
        InvocationHandler handler = new ProxySubject(real);
        Subject subject = (Subject) Proxy.newProxyInstance(real.getClass().getClassLoader(),
                real.getClass().getInterfaces(), handler);
        subject.hello("liwenlei");
        subject.rent();


        StatisticProxy statisticProxy = new StatisticProxy(real);
        statisticProxy.hello("liwenlie");
        statisticProxy.rent();

        //动态代理与静态代理相比较，
        // 最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke）。
        // 这样，在接口方法数量比较多的时候，我们可以进行灵活处理，而不需要像静态代理那样每一个方法进行中转。
        // 而且动态代理的应用使我们的类职责更加单一，复用性更强
    }
}
