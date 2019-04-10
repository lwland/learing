package learn.lwl.java8;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Charte1 {
//    @FunctionalInterface
//    interface  RunnerEx{
//        void run()throws  Exception;
//
//    }
//    public static Runnable uncheck(RunnerEx runnerEx){
//        return ()-> {
//            try {
//                runnerEx.run();
//            }catch (Exception e){
//                System.out.println("error");
//            }
//        };
//    }
//    public static Runnable andThen(Runnable r1,Runnable r2){
//        return()->{
//            r1.run();
//            r2.run();
//        };
//    }
//    @FunctionalInterface
//    interface Collection2<T> extends Collection<T>{
//        default void forEachIf(Consumer<T> action, Predicate<T> filter){
//            forEach(item->{
//                if(filter.test(item)) action.accept(item);
//            });
//        }
//    }
    interface  F{
        default void f(){

        };
        abstract void f1();

        static  void f3(){}
}
   interface  L{
       default void f(){

       };
       abstract void f1();

       static  void f3(){}
   }
   class S implements F,L{

       @Override
       public void f() {
           F.super.f();
       }
       @Override
       public void f1() {
       }
   }

    public static void main(String[] args) {
        File file=new File("/etc");
//        File[] list=file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isDirectory();
//            }
//        });
//        Arrays.stream(list).forEach(System.out::println);
//        File[] list2=file.listFiles(x->x.isDirectory());
//        Arrays.stream(list2).forEach(System.out::println);
//        file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return false;
//            }
//        });
//        String[] list3=file.list((File dir, String name)->name.endsWith("txt"));
//        Arrays.stream(list3).forEach(System.out::println);

//
//        File[] list4=file.listFiles();
//        Arrays.sort(list4,(first,second)->{
//            if(first.isDirectory()&&second.isDirectory()||first.isFile()&&second.isFile()){
//                return first.getPath().compareTo(second.getPath());
//            }else {
//                if(first.isDirectory()) return 1;
//                return -1;
//            }
//        });
//        Arrays.stream(list4).forEach(System.out::println);
//        new Thread(uncheck(()->{
//            System.out.println("1000");
//            Thread.sleep(1000);
//      })).start();
//        new Thread(andThen(()->{
//            System.out.println("runner1");
//        }, ()->{
//            System.out.println("runner2");
//        })).start();
        String[] list={"1","12","123"};
        List<Runnable> runnables=new ArrayList<>();
        for(String name:list){
            runnables.add(()->{
                System.out.println(name);
            });
        }
       runnables.forEach(runnable -> {
//           new Thread(runnable).start();
           runnable.run();
       });
    }
}


