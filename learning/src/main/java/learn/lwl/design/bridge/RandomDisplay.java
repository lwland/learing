package learn.lwl.design.bridge;

public class RandomDisplay extends Display {
    public RandomDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void randomDisplay(int times) {
        open();
        Integer count = (int) (Math.random() * times);
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            print();
        }
        close();
    }
}
