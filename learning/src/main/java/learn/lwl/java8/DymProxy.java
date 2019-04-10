package learn.lwl.java8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DymProxy {
    public static void main(String[] args) {
        Calculator calculator=new CalculatorImpl();
        Calculator proxied= (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader()
                ,calculator.getClass().getInterfaces()
        ,new AddHandler(calculator));
        System.out.println(proxied.add(1,2,3));
    }
}
interface Calculator{
    int calculate(int x,int y);
    int add(int x,int y,int z);
}
class CalculatorImpl implements  Calculator{

    @Override
    public int calculate(int x, int y) {
        return x*y;
    }

    @Override
    public int add(int x, int y,int z) {
        return x+y+z;
    }

}
class AddHandler implements InvocationHandler{
    private Object target;

    public AddHandler(Object target) {
        this.target = target;
    }

    public void prepare(){
        System.out.println("prepare");
    }
    public void after(){
        System.out.println("after");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prepare();
        System.out.println("invoke");
        Object result=method.invoke(target,args);
        after();
        return result;
    }
}
