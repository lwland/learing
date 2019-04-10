package learn.lwl.design.prototype;

public class Client {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen ul = new UnderlinePen('~');
        MessageBox sbox = new MessageBox('/');
        MessageBox xbox = new MessageBox('*');
        manager.register(ul, "ul");
        manager.register(sbox, "sbox");
        manager.register(xbox, "xbox");
        Product p1 = manager.create("ul");
        p1.user("hello world!!!");
        System.out.println();
        Product p2 = manager.create("sbox");
        p2.user("hello world!!!");
        System.out.println();
        Product p3 = manager.create("xbox");
        p3.user("hello world!!!");

    }
}
