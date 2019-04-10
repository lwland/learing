package learn.lwl.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class lamdaExpress {

    public static void main(String[] args){
//        Comparator<Apple> byColor=new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getColor().compareTo(o2.getColor());
//            }
//        };
//        List<Apple> apples= Arrays.asList(
//                new Apple(150L,"red"),
//                new Apple(120L,"green"),
//                new Apple(125L,"green"));
//        Apple object= new Apple(150L,"red");
//        apples.sort(((o1, o2) -> o1.getColor().compareTo(o2.getColor())));
//        System.out.println(apples);
//
//        Function<Apple, Boolean> fLamda=apple-> apple.getColor().equals("green");
//        System.out.println(fLamda.apply(object));
//
//        Predicate<Apple> predicate=apple -> apple.getColor().equals("green");
//        System.out.println(predicate.and(predicate));
//
//        Supplier<Apple> supplier=Apple::new;
//        System.out.println(supplier.get());
//
//        Test t=(int x,int y)->{
//            System.out.println(x);
//            System.out.println(y);
//        };
//        t.test(1,2);
//        Test3 t3=()->{};
//
//        Test2 t2=()->System.out.println("hello");
//        Callable<Integer> runnable=()->{
//            Thread.sleep(1000);
//            return 1;
//        };
        String[] test={"1","123","12","122222"};
        Arrays.sort(test);

    }

    @FunctionalInterface
    public interface Test{
        void test(int x, int y);
    }
    @FunctionalInterface
    public interface Test2{
        void test2();
    }
    @FunctionalInterface
    public interface Test3{
        void test3();
    }
}
