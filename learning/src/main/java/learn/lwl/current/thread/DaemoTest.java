package learn.lwl.current.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class DaemoTest {
    public static void main(String[] args) {
        Thread daemo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("daemo is active");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("daemo finally block");
                    }
                }
            }
        });
        daemo.setDaemon(true);
        daemo.start();
        Thread userThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        userThread.start();
        new CopyOnWriteArrayList<>();
        Vector vector = new Vector();
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        Collections.synchronizedList(new ArrayList<>());//所有方法都做了同步
        Collections.unmodifiableList(new ArrayList<>());//所有修改操作都会抛出UnsupportedOperationException
    }
}
