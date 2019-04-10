package learn.lwl.tomcat.container;

import learn.lwl.tomcat.MyHttpRequest;
import learn.lwl.tomcat.MyHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/9
 * @Time:10:51
 **/
public class MyHttpServer1 {
    private final String SHUTDOWN_COMMAND = "shutdown";
    private boolean shutdown = false;

    public static void main(String[] args) {
        MyHttpServer1 server1 = new MyHttpServer1();
        server1.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        while (!shutdown) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                MyHttpRequest request = new MyHttpRequest(inputStream);
                request.parse();
                MyHttpResponse response = new MyHttpResponse(outputStream);
                response.setRequest(request);
                if (request.getUri().startsWith("/servlet/")) {
                    SevletProcessor sevletProcessor = new SevletProcessor();
                    sevletProcessor.process(request, response);

                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
