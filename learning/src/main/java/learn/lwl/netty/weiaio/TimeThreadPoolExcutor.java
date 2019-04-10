package learn.lwl.netty.weiaio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeThreadPoolExcutor {
    private ExecutorService executor;

    public TimeThreadPoolExcutor(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize)
        );
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
