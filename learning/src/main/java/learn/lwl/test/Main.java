package learn.lwl.test;

public class Main {
    //    public static void main(String[] args) {
//        Fruit f1 = new Fruit("f1", 1);
//        Fruit f2 = new Fruit("f2", 2);
//        Apple a1 = new Apple("a1", 11, "red");
//        Apple a2 = new Apple("a2", 12, "green");
//        List<Fruit> fruits = new ArrayList<>();
//        fruits.add(f1);
//        fruits.add(f2);
//        fruits.add(a1);
//        fruits.add(a2);//可以
//        fruits.forEach(x -> System.out.println(x.toString()));
//        //结论：子类可以添加导父类集合
//        List<Apple> apples = new ArrayList<>();
//        apples.add(a1);
//        apples.add(a2);
//        apples.forEach(System.out::println);
////        apples.add(f1)//编译报错
//        //结论：父类不可以添加导子类集合
//        List<IFruit> iFruits = new ArrayList<>();
//        iFruits.add(a1);
//        iFruits.add(a2);
//        iFruits.forEach(System.out::println);
////        iFruits.add(f1)//编译报错
//        //结论：实现类可以添加到接口的集合中
////        fruits=apples;//编译错误
////        apples=fruits;//编译错误
////        iFruits=apples;//编译错误
//        //结论：不可以相互赋值
//        Fruit f3 = new Apple("a3", 13, "yellow");
//        System.out.println(f3);
//
//        IFruit f4 = new Apple("a4", 14, "blue");
//        System.out.println(f4);
//
//
//    }
    public static void main(String[] args) {
        Fruit f1 = new Apple();
        f1.setName("apple");
        Fruit f2 = new Orange();
        f2.setName("orange");
        System.out.println(f1.getName());
        System.out.println(f2.getName());
    }
}
