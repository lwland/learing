package learn.lwl.tomcat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/4
 * @Time:20:48
 **/
class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("hello world");
                writer.flush();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
