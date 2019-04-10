package learn.lwl.current.producer_custom.version_2;

import learn.lwl.current.producer_custom.PcData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        List<PcData> list = new ArrayList<>();
        Customer c1 = new Customer(list);
        Customer c2 = new Customer(list);
        Customer c3 = new Customer(list);
        Producer p1 = new Producer(list,10);
        Producer p2 = new Producer(list,10);
        Producer p3 = new Producer(list,10);
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
