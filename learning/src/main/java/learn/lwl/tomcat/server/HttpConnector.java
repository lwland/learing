package learn.lwl.tomcat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/9
 * @Time:10:08
 **/
public class HttpConnector implements Runnable {
    boolean stoped = false;
    private String schema = "http";

    public String getSchema() {
        return schema;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ServerSocket serversocket = null;
        int port = 8080;
        try {
            serversocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stoped) {
            Socket socket = null;
            try {
                socket = serversocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }
}
