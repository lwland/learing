package learn.lwl.current.thread;

import java.util.concurrent.*;

public class InterruptTest {
    public static void test() {
        System.out.println(Thread.currentThread().getName() + " begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                test();
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        });
        thread2.start();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                test();
                return 100;
            }
        });
        System.out.println(future.get());
        Future<Integer> future1 = service.submit(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                test();
                return 100;
            }
        }), 10);
        System.out.println(future1.get());
    }
}
