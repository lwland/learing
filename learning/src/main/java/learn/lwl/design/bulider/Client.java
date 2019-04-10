package learn.lwl.design.bulider;

public class Client {
    public static void main(String[] args) {
        boolean flag = false;
        if (flag) {
            HtmlBuilder builder = new HtmlBuilder("test");
            Director director = new Director(builder);
            director.construct();
            System.out.println(builder.getResult());
        } else {
            TextBuilder builder = new TextBuilder();
            Director director = new Director(builder);
            director.construct();
            System.out.println(builder.getResult());
        }
    }
}
