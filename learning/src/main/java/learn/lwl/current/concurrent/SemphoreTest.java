package learn.lwl.current.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemphoreTest<T> {
    //构造一个阻塞有接队列
    private final Set<T> set;
    private final Semaphore semaphore;

    public SemphoreTest(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean isAdded = false;
        try {
            if (set.add(o)) {
                isAdded = true;
            }
            return isAdded;
        } finally {
            if (!isAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean isRemoved = false;
        if ((isRemoved = set.remove(o))) {
            semaphore.release();
        }
        return isRemoved;
    }

}
