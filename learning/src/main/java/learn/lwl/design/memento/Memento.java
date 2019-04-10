package learn.lwl.design.memento;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private int money;
    private ArrayList<String> fruits;

    public Memento(int money) {
        this.money = money;
        this.fruits = new ArrayList<>();
    }

    public void addFruit(String fruit) {
        fruits.add(fruit);
    }

    List<String> getFruits() {
        return (List<String>) fruits.clone();
    }

    public int getMoney() {
        return money;
    }
}
