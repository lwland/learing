package learn.lwl.design.strategy;

public class Hand {
    private static final Integer HANDVALUE_GUU = 0;
    private static final Integer HANDVALUE_CHO = 1;
    private static final Integer HANDVALUE_PAA = 2;
    private Integer handValue;

    private Hand(Integer handValue) {
        this.handValue = handValue;
    }

    public Integer getHandValue() {
        return handValue;
    }

    public void setHandValue(Integer handValue) {
        this.handValue = handValue;
    }

    public static final Hand[] hands = new Hand[]{
            new Hand(HANDVALUE_GUU),
            new Hand(HANDVALUE_CHO),
            new Hand(HANDVALUE_PAA)
    };

    public static Hand getHand(Integer handValue) {
        return hands[handValue];
    }

    public boolean isStronger(Hand h2) {
        return fight(h2) == 1;
    }

    public boolean isWeaker(Hand h2) {
        return fight(h2) == -1;
    }

    private int fight(Hand h2) {
        if (this == h2) {
            return 0;//平
        } else if ((this.handValue + 1) % 3 == h2.getHandValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
