package learn.lwl.design.factory;

public class Client {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product c1 = factory.create("123");
        Product c2 = factory.create("234");
        Product c3 = factory.create("345");
        c1.use();
        c2.use();
        c3.use();

    }
}
