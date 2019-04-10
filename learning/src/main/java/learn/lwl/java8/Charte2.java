package learn.lwl.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Charte2 {

    public static void main(String[] args) {
//        Stream<String> stringStream=Stream.of("123","234","345");
//        Stream<Double> doubleStream=Stream.generate(Math::random);
//        Stream.iterate(1.0,p->p*2).peek(e->System.out.println(e)).limit(20).toArray();
//        Stream.of("123","234","345").map(str->characterStream(str)).forEach(x->x.forEach(y->System.out.print(y)));
//        Stream.of("123","234","345").flatMap(str->characterStream(str)).forEach(x->System.out.println(x));
//        Optional<String> test=Optional.empty();
//        List<String> list=new ArrayList<>();
//        test.ifPresent(list::add);
//        list.add(test.orElse("234"));
//        list.add(test.orElseGet(()->"123"));
//        list.add(test.orElseThrow(NoSuchElementException::new));
//        System.out.println(list.size());
//        String testNull=null;
//        Optional<String> op1=Optional.ofNullable(testNull);
//        System.out.println(op1.get());
//        Optional<String> op=Optional.of(testNull);
//        System.out.println(op.get());
        Optional<Double> doubleOptional=Optional.of(4.0).flatMap(Charte2::inverse).flatMap(Charte2::squrt);
        System.out.println(doubleOptional.orElse(0.0));

    }
    private static Optional<Double> inverse(Double d){
        return d==0?Optional.empty():Optional.of(d);
    }
    private static Optional<Double> squrt(Double d){
        return d<0?Optional.empty():Optional.of(d*d);
    }
    private static Stream<Character> characterStream(String str){
        List<Character> list=new ArrayList<>();
        for(Character c:str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
}
