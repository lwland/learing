package learn.lwl.current.producer_custom.version_2;

import learn.lwl.current.producer_custom.PcData;

import java.util.List;
import java.util.Random;

public class Customer implements Runnable {
    private List<PcData> list;
    private volatile boolean isRunning = true;
    private final int sleep = 1000;

    public Customer(List<PcData> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(sleep));
                synchronized (list) {
                    if (list.size() == 0) {
                        list.wait();
                        list.notifyAll();
                    } else {
                        if (list.size() > 0) {
                            PcData pcData = list.remove(0);
                            System.out.println("customer" + Thread.currentThread().getId() + "has custom " + pcData.toString());
                        }
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
