package learn.lwl.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {
    @FunctionalInterface
    public interface AppleFilte{
        // 有切只有一个方法，function接口 default 可以
        boolean filte(Apple apple);
    }
    public List<Apple> filteGreenApples(List<Apple> apples){
        List<Apple> greenApples=new ArrayList<>();
        for(Apple apple:apples){
           if("green".equals(apple.getColor())){
               greenApples.add(apple);
           }
        }
        return greenApples;
    }
    public List<Apple> filteAppleByFilter(List<Apple> apples,AppleFilte appleFilte){
        List<Apple> lists=new ArrayList<>();
        for(Apple apple:apples){
            if(appleFilte.filte(apple)){
                lists.add(apple);            }
        }
        return lists;
    }
    public List<Apple> filteAppleByColor(List<Apple> apples,String color){
        List<Apple> filteApples=new ArrayList<>();
        for(Apple apple:apples){
            if(color.equals(apple.getColor())){
                filteApples.add(apple);
            }
        }
        return filteApples;
    }
    public static void  main(String[] args) throws InterruptedException {
        List<Apple> apples= Arrays.asList(
                new Apple(150L,"red"),
                new Apple(120L,"green"),
                new Apple(125L,"green"));
//        List<Apple> greenApples=new FilterApple().filteGreenApples(apples);
//        assert greenApples.size()==2;
//        System.out.println(greenApples);
//        List<Apple> filteApples=new FilterApple().filteAppleByColor(apples,"red");
//        assert filteApples.size()==2;
//        System.out.println(filteApples);
//        List<Apple> filteApples=new FilterApple().filteAppleByFilter(apples, new AppleFilte() {
//            @Override
//            public boolean filte(Apple apple) {
//                return apple.getWeight()>160&&"red".equals(apple.getColor());
//            }
//        });
//        assert filteApples.size()==2;
//        System.out.println(filteApples);
        //lamda 表达式
//        List<Apple> lamdaApples=new FilterApple().filteAppleByFilter(apples,apple -> {
//             return apple.getColor().equals("red");
//        });
//        assert lamdaApples.size()==2;
//        System.out.println(lamdaApples);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        new Thread(()->System.out.println(Thread.currentThread().getName())).start();
        Thread.currentThread().join();
    }
}
