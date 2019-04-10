package learn.lwl.design.Singleton;

public class Client {
    public static void main(String[] args) {
        Singleton o1=Singleton.getInstance();
        System.out.println(o1);
        Singleton o2=Singleton.getInstance();
        System.out.println(o2);
        if(o1==o2){
            System.out.println("o1和o2是同一个实例");
        }else{
            System.out.println("o1和o2不是同一个实例");
        }
    }
}
