package learn.lwl.design.Singleton;

public class Singleton {
    /**
     * 饿汉式实现。
     * 饿汉就是类一旦加载就把单例初始化完成，保证getInstance的时候一定存在。
     * 天生线程安全，但是无论是不是使用都会创建一个实例，占据一定内存。相应的第一次调用的时候速度会快一些
     */
    private static final Singleton singleton = new Singleton();
    private Singleton() {
        System.out.println("创建了一个singleton实例");
    }
    public static Singleton getInstance() {
        return singleton;
    }
    /**
     * 懒汉式实现
     * 延迟加载，第一次使用时才会创建实例
     * 需要确保线程安全。确保线程安全有三种实现
     */
    private static Singleton lazy = null;
    //线程不安全，并发情况下可能创建多个实例
    public static final Singleton getInstance1() {
        if (lazy == null) {
            lazy = new Singleton();
        }
        return lazy;
    }
    //1、在getInstance上加同步，每次调用都要同步,只有第一次调用需要
    public static synchronized final Singleton getInstance2() {
        if (lazy == null) {
            lazy = new Singleton();
        }
        return lazy;
    }
    //2、双重检查锁定，第一次检查为了判断是否已存在实例，第二次是避免第一次检查到锁类之间其他类已经创建实例，
    //只有第一次调用才会进行同步
    public static final Singleton getInstance3() {
        if (lazy == null) {
            synchronized (Singleton.class) {
                if (lazy == null) {
                    lazy = new Singleton(); }
            }
        }
        return lazy;
    }
    //3、静态内部类  即实现了线程安全，又避免了同步的性能问题
    //利用classloader的机制来保证初始化instance时只有一个线程
    public static class LazySingleton {
        public static final Singleton lazy = new Singleton();
    }
    public static final Singleton getInstance4() {
        return LazySingleton.lazy;
    }
}
