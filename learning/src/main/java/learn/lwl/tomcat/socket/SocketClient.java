package learn.lwl.tomcat.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/4
 * @Time:20:10
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream out = socket.getOutputStream();
        boolean autoFlush = true;
        PrintWriter writer = new PrintWriter(out, autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.println("GET /index.jsp HTTP/1.1");
        writer.println("Host: localhost:8080");
        writer.println("Connection:Close");
        writer.println();

        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }
        System.out.println(sb.toString());
        socket.close();
    }
}
