package learn.lwl.reactivex;

import java.util.Observable;

public class ObserverModel extends Observable {
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    public static void print(String msg) {
        System.out.println(Thread.currentThread() + ":" + msg);
    }

    public static void main(String[] args) {
        //同步非阻塞
        ObserverModel observable = new ObserverModel();
        print("添加通知者");
        observable.addObserver((o, v) -> {
            print("1 观察者接受通知");
        });
        observable.addObserver((o, v) -> {
            print("2 观察者接受通知");
        });
        observable.addObserver((o, v) -> {
            print("3 观察者接受通知");
        });

        print("通知观察者");
        observable.setChanged();
        observable.notifyObservers();

        //异步
        new Thread(() -> {
            print("1 线程中");
        }).start();
        new Thread(() -> {
            print("2 线程中");
        }).start();
        new Thread(() -> {
            print("3 线程中");
        }).start();

    }
}
