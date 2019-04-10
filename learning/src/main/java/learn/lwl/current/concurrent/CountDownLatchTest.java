package learn.lwl.current.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CountDownLatchTest {
    public long timeTsask(int nThread, final Consumer task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.accept(null);
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        long begin = System.nanoTime();
        startGate.countDown();//统一开启线程
        endGate.await();
        long end = System.nanoTime();
        return end - begin;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new CountDownLatchTest().timeTsask(10, (x) -> {
            try {
                System.out.println("task run");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("task end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }));
    }


}
