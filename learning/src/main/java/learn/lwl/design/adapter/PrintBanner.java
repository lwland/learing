package learn.lwl.design.adapter;

public class PrintBanner extends Banner implements Print {
    public PrintBanner(String str) {
        super(str);
    }

    @Override
    public void printStrong() {
        showWithAster();
    }

    @Override
    public void printWeek() {
        showWithParent();
    }
}
