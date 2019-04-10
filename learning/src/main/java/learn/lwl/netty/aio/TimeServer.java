package learn.lwl.netty.aio;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        AsynTimeServerHandler asynTimeServerHandler = new AsynTimeServerHandler(port);
        new Thread(asynTimeServerHandler).start();
    }
}
