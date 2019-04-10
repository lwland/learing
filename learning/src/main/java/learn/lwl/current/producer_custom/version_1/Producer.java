package learn.lwl.current.producer_custom.version_1;

import learn.lwl.current.producer_custom.PcData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private BlockingQueue<PcData> queue;
    private volatile boolean isRunning;
    private static AtomicInteger num = new AtomicInteger();
    private final int sleep = 1000;

    public Producer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        PcData pcData = null;
        Random random = new Random();
        System.out.println("producer" + Thread.currentThread().getId() + "start");
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(sleep));
                System.out.println("producer" + Thread.currentThread().getId() + "is producing data");
                pcData = new PcData(num.incrementAndGet(), "pcData");
                System.out.println(pcData.toString() + "has been produce and now put queue");
                if (!queue.offer(pcData, 2, TimeUnit.SECONDS)) {
                    System.out.println(pcData.toString() + "enter queue failed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }

    }
}
