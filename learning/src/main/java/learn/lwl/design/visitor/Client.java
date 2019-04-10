package learn.lwl.design.visitor;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        File f1 = new File("f1.html", 100);
        File f2 = new File("f2.html", 200);
        File f3 = new File("f3.html", 200);
        Directory child1 = new Directory("child1");
        Directory child2 = new Directory("child2");
        child1.add(f1);
        child1.add(f2);
        child2.add(f3);
        child1.add(child2);
        root.add(child1);
//        root.printlnList();
//        System.out.println(f3.getPath());
        Visitor visitor = new ListVisitor();
        root.accept(visitor);
        System.out.println("1111111111");
        FindFileVisitor visitor1 = new FindFileVisitor("html");
        root.accept(visitor1);
        Iterator<File> iterator = visitor1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("1111111111");
        SizeVisitor visitor2 = new SizeVisitor();
        root.accept(visitor2);

        System.out.println(visitor2.getSize());
        System.out.println("1111111111");
        ElementArrayList elementArrayList=new ElementArrayList();
        elementArrayList.add(root);
        elementArrayList.add(new File("123.txt",100));
        elementArrayList.accept(new ListVisitor());
    }
}
