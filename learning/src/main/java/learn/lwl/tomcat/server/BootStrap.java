package learn.lwl.tomcat.server;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/9
 * @Time:10:08
 **/
public class BootStrap {
    public static void main(String[] args) {
        HttpConnector connector=new HttpConnector();
        connector.start();
    }
}
