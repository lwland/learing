package learn.lwl.netty.nio;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        String ip = "127.0.0.1";
        new Thread(new TimeClientHandle(port, ip), "TimeClientHandle-thread-1").start();
    }
}
