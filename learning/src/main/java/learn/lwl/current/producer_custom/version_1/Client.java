package learn.lwl.current.producer_custom.version_1;

import learn.lwl.current.producer_custom.PcData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PcData> queue = new LinkedBlockingDeque<>();
        Customer c1 = new Customer(queue);
        Customer c2 = new Customer(queue);
        Customer c3 = new Customer(queue);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);
        service.submit(p1);
        service.submit(p2);
        service.submit(p3);
        Thread.sleep(100 * 1000);
        p1.stop();
        p2.stop();
        p3.stop();
        c1.stop();
        c2.stop();
        c3.stop();
        service.shutdown();
    }
}
