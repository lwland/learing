package learn.lwl.netty;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeHandler implements Runnable {
    private Socket socket;

    public TimeHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = reader.readLine();
                if (body == null) {
                    break;
                } else {
                    System.out.println("the time server received order " + body);
                    currentTime = "Time".equals(body) ? LocalDateTime.now().toString() : "bad request";
                    writer.println(currentTime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
