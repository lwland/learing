package learn.lwl.java8;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.atomic.AtomicLong;

public class NewThead {
    public static void main(String[] args) {
        Long commonLong=0L;
        System.out.println(commonLong);
        for(int i=0;i<10;i++){
            new Thread(()->{
                long id= commonLong;
                System.out.println(Thread.currentThread().getId()+":id="+id);
            }).start();
        }
        AtomicLong atomicLong=new AtomicLong();
        System.out.println(atomicLong.get());
        for(int i=0;i<10;i++){
            new Thread(()->{
//               long id= atomicLong.incrementAndGet();
               atomicLong.updateAndGet(x->Math.max(atomicLong.get(),5));
               System.out.println(Thread.currentThread().getId()+":id="+atomicLong.get());
            }).start();
        }
    }
}
