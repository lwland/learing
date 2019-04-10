package learn.lwl.design.strategy;

import java.util.Random;

public class WinStrategy implements Strategy {
    private Hand preHand;
    private boolean win = false;
    private Random random;

    public WinStrategy(int seed) {

        this.random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if (!win) {
            preHand = Hand.getHand(random.nextInt(3));
        }
        return preHand;
    }

    @Override
    public void study(boolean win) {
        this.win = win;
    }
}
