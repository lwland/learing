package learn.lwl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//代理对象，将其方法暴漏给用户调用。
// 动态代理是在系统运行时动态的创建代理类，一个代理类能够代理多中角色
//动态代理需要实现InvocationHandler接口，由其invoke方法调用具体的实现逻辑
public class ProxySubject implements InvocationHandler {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @param proxy  代理对象
     * @param method 调用的方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before " + method.getName() + "execute");
        Object o = method.invoke(subject, args);
        System.out.println("after " + method.getName() + "execute");
        return o;
    }
}
