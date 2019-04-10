package learn.lwl.design.memento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Gamer {
    private int money;
    private List<String> fruits = new ArrayList<>();
    private Random random = new Random();
    private String[] fruitName = {
            "苹果", "橘子", "香蕉", "哈密瓜"
    };

    public Gamer(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void bet() {
        int dice = random.nextInt(6) + 1;
        if (dice == 1) {
            money += 100;
            System.out.println("money add 100");
        } else if (dice == 2) {
            money /= 2;
            System.out.println("money havled");
        } else if (dice == 6) {
            String fruit = getFruit();
            fruits.add(fruit);
            System.out.println("fruits add");
        } else {
            System.out.println("nothing happen");
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento(money);
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String f = iterator.next();
            if (f.startsWith("好吃的")) {
                memento.addFruit(f);
            }
        }
        return memento;
    }

    public void restoreMemento(Memento memento) {
        this.money = memento.getMoney();
        this.fruits = memento.getFruits();
    }

    public String getFruit() {
        String prefix = "";
        if (random.nextBoolean()) {
            prefix = "好吃的";
        }
        return prefix + fruitName[random.nextInt(fruitName.length)];
    }
}
