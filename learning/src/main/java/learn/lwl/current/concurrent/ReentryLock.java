package learn.lwl.current.concurrent;

public class ReentryLock {
    public synchronized void dosomeThing() {
        System.out.println("ReentryLock");
    }
}
