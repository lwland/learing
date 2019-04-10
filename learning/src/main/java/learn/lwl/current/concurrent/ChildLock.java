package learn.lwl.current.concurrent;

public class ChildLock extends ReentryLock {
    @Override
    public synchronized void dosomeThing() {
        System.out.println("ChildLock");
        super.dosomeThing();
    }

    public static void main(String[] args) {
        new ChildLock().dosomeThing();
    }
}
