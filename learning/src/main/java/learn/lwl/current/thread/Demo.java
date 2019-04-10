package learn.lwl.current.thread;

public class Demo extends Thread {
    public static  int count = 0;
    //
    public static void incr() {
        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        //保证并发的正确：原子性、可见性、有序性
        //线程和内存的交互
        //JMM（java memory model，定义了jvm和计算机主存交互的模型）主内存、工作内存（线程的本地内存cpu的寄存器和高速缓存的抽象描述）
        //http://tutorials.jenkov.com/java-concurrency/java-memory-model.html
        //https://www.cnblogs.com/dingyingsi/p/3760447.html
        //JVM（运行时内存区）
        //getstatic
        //count add
        //putstatic lock#

        //volatile 保证可见性，通过内存屏障防止指令重排序 触发lock指令
        //Lock锁
        //1、总线锁或者高速缓存锁
        //2、lock回写已修改的数据到主存，使其他cpu缓存的数据无效
        //3、内存屏障

        //synchronized 访问count++引入和排队机制，使修改值变成顺序
        //锁的范围
        //偏向锁、轻量锁、重量锁 获取锁的代价递增无法获取锁时锁定策略依次升级
        //锁（资源和锁的对象）对象头的方式保存锁

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {

            new Thread(() -> Demo.incr()).start();

        }
        Thread.sleep(2000);
        System.out.println(count);
    }
}
