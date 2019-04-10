package learn.lwl.design.composite;

public class Client {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        File f1 = new File("f1", 100);
        File f2 = new File("f2", 200);
        File f3 = new File("f3", 200);
        Directory child1 = new Directory("child1");
        Directory child2 = new Directory("child2");
        child1.add(f1);
        child1.add(f2);
        child2.add(f3);
        child1.add(child2);
        root.add(child1);
        root.printlnList();
        System.out.println(f3.getPath());

    }
}
