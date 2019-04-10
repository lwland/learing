package learn.lwl.design.bulider;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildTitle("Greeting");
        builder.buildString("从早上到中午");
        builder.buildItems(new String[]{"早上好", "下午好"});
        builder.buildItems(new String[]{"晚上好", "晚安", "再见"});
        builder.buildDone();
    }
}
