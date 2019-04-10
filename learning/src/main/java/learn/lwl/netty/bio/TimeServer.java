package learn.lwl.netty.bio;

import learn.lwl.netty.TimeHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("the time server is to listen " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    new Thread(new TimeHandler(socket)).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
