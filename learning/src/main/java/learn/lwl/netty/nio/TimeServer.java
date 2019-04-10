package learn.lwl.netty.nio;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        MultiTimeServer multiTimeServer = new MultiTimeServer(port);
        new Thread(multiTimeServer, "NIO-multiTimeServer-001").start();
    }

}
