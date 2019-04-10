package learn.lwl.design.obeserver;

public class Client {
    public static void main(String[] args) {
        NumGenerator numGenerator = new IncreaseNumGenerator();
        Observer o1 = new DigitalObserver();
        Observer o2 = new GraphObserver();
        numGenerator.add(o1);
        numGenerator.add(o2);
        numGenerator.execute();

    }

}
