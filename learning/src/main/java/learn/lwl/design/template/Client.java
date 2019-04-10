package learn.lwl.design.template;

public class Client {
    public static void main(String[] args) {
        AbstractDisplay display = new StringDisPlay("hello world");
        display.display();
        AbstractDisplay charDis = new CharDisPlay('H');
        charDis.display();
    }
}
