package learn.lwl.design.adapter;

public class Banner {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Banner(String str) {
        this.str = str;
    }

    public void showWithAster() {
        System.out.println("showWithAster: (" + str + ")");
    }

    public void showWithParent() {
        System.out.println("showWithParent: *" + str + "*");
    }
}
