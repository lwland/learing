package learn.lwl.design.strategy;

public class Client {
    public static void main(String[] args) {
        int seed1 = 3;
        int seed2 = 2;
        Player player1 = new Player("lwl", new WinStrategy(seed1));
        Player player2 = new Player("jjj", new ProdStrategy(seed2));
        for (int i = 0; i < 1000; i++) {
            Hand hand1 = player1.nextHand();
            Hand hand2 = player2.nextHand();
            if (hand1.isStronger(hand2)) {
                player1.win();
                player2.lose();
            } else if (hand1.isWeaker(hand2)) {
                player1.lose();
                player2.win();
            } else {
                player1.even();
                player2.even();
            }
        }
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }
}
