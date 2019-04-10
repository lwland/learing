package learn.lwl.design.adapter;

public class PrintBanner2 implements Print {
    private Banner banner;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public PrintBanner2(Banner banner) {
        this.banner = banner;
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }

    @Override
    public void printWeek() {
        banner.showWithParent();
    }
}
