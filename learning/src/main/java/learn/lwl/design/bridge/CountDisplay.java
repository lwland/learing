package learn.lwl.design.bridge;

public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void mulDisplay(Integer count) {
        open();
        for (int i = 0; i < count; i++) {
            print();
        }
        close();
    }
}
