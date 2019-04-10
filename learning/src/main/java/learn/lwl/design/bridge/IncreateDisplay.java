package learn.lwl.design.bridge;

public class IncreateDisplay extends CountDisplay {
    private Integer count;

    public IncreateDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void increaceDisplay(Integer level) {
        for (int i = 0; i < level; i++) {
            mulDisplay(i);
        }

    }
}
