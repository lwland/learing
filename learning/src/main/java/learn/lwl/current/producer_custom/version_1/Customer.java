package learn.lwl.current.producer_custom.version_1;

import learn.lwl.current.producer_custom.PcData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable {
    private BlockingQueue<PcData> queue;
    private volatile boolean isRunning;
    private final int sleep = 1000;

    public Customer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        isRunning = true;
        Random random = new Random();
        System.out.println("customer" + Thread.currentThread().getId() + "start");
        while (isRunning) {
            PcData pcData = null;
            try {
                Thread.sleep(random.nextInt(sleep));
                pcData = queue.take();
                if (pcData != null) {
                    System.out.println("Customer" + Thread.currentThread().getId() + "has custom" + pcData.toString());
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
