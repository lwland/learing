package learn.lwl.design.chain_of_responsibility;

public class Client {
    public static void main(String[] args) {
        Support o1 = new NoSupport("NoSupport");
        Support o2 = new LimitSupport("LimitSupport", 50);
        Support o3 = new OddSupport("OddSupport");
        Support o4 = new SpecialSupport("SpecialSupport", 428);
        Support o5 = new SpecialSupport("SpecialSupport", 208);
        Support o6 = new SpecialSupport("SpecialSupport", 228);
        Trouble trouble = null;
        o1.setNext(o2).setNext(o4).setNext(o3).setNext(o5).setNext(o6);
        for (int i = 20; i < 500; i++) {
            trouble = new Trouble(i);
            System.out.print(trouble);
            o1.support(trouble);
        }

    }
}
