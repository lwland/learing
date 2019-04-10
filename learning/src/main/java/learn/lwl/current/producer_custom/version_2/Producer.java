package learn.lwl.current.producer_custom.version_2;

import learn.lwl.current.producer_custom.PcData;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private List<PcData> list;
    private int length;
    private volatile boolean isRunning = true;
    private final int sleep = 1000;
    private static AtomicInteger num = new AtomicInteger();

    public Producer(List<PcData> list, int length) {
        this.list = list;
        this.length = length;
    }

    @Override
    public void run() {
        Random random = new Random();
        PcData pcData = null;
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(sleep));

                synchronized (list) {
                    if (list.size() >= length) {
                        list.wait();//释放锁
                        list.notifyAll();//通知一个等待锁的线程
                    } else {
                        pcData = new PcData(num.incrementAndGet(), "pcData");
                        list.add(pcData);
                        System.out.println("producer" + Thread.currentThread().getId() + " produced" + pcData.toString());
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
