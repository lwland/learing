package learn.lwl.design.bridge;

public class Client {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("hello world"));
        CountDisplay d2 = new CountDisplay(new StringDisplayImpl("hello exceed"));
        RandomDisplay d3 = new RandomDisplay(new StringDisplayImpl("hello random"));
        d1.display();
        d2.mulDisplay(5);
        d3.randomDisplay(10);
        Display d4 = new Display(new FileDisplay("out.txt"));
        d4.display();
        IncreateDisplay d5 = new IncreateDisplay(new DecoratorDisplay('<', '>', '*'));
        d5.increaceDisplay(3);
        IncreateDisplay d6 = new IncreateDisplay(new DecoratorDisplay('|', '-', '#'));
        d6.increaceDisplay(5);
    }
}
