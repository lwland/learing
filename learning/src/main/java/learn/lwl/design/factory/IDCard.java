package learn.lwl.design.factory;

public class IDCard extends Product {
    private String owner;

    public String getOwner() {
        return owner;
    }

    public IDCard(String owner) {
        System.out.print("制作" + owner + "的IDCard");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.print("使用" + owner + "的IDCard");
    }
}
