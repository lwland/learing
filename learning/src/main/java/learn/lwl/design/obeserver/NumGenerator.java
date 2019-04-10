package learn.lwl.design.obeserver;

import java.util.ArrayList;
import java.util.List;

public abstract class NumGenerator {
    private List<Observer> observers = new ArrayList<>();

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObserver() {
        observers.stream().forEach(x -> {
            x.update(this);
        });
    }

    public abstract int getNum();

    public abstract void execute();
}
