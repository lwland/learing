package learn.lwl.design.obeserver;

public class GraphObserver implements Observer {
    @Override
    public void update(NumGenerator numGenerator) {
        int count = numGenerator.getNum();
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println("");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
