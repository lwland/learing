package learn.lwl.design.obeserver;

import java.util.Random;

public class RandomNumGenerator extends NumGenerator {
    private Random random = new Random();
    private Integer num;

    public int getNum() {
        return num;
    }

    @Override
    public void execute() {
        for (int i = 0; i < 20; i++) {
            num = random.nextInt(50);
            notifyObserver();
        }

    }

}
