package learn.lwl.netty.weiaio;

import learn.lwl.netty.TimeHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        Socket socket = null;
        TimeThreadPoolExcutor poolExcutor = new TimeThreadPoolExcutor(50, 1000);
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                socket = serverSocket.accept();
                poolExcutor.execute(new TimeHandler(socket));
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

