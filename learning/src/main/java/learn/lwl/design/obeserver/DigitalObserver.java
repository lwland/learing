package learn.lwl.design.obeserver;

public class DigitalObserver implements Observer {
    @Override
    public void update(NumGenerator numGenerator) {
        System.out.println("DigitalObserver:"+numGenerator.getNum());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
