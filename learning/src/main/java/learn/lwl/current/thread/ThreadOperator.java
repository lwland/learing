package learn.lwl.current.thread;

public class ThreadOperator {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread3.start();

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) ;
            }
        });
        thread2.start();
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupted();
        while (thread1.isInterrupted()) ;
        System.out.println("sleepThread isInterrupted: " + thread1.isInterrupted());
        System.out.println("busyThread isInterrupted: " + thread2.isInterrupted());
        System.out.println("waitThread isInterrupted: " + thread3.isInterrupted());
    }
}
