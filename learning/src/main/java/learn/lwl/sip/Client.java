package learn.lwl.sip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        ParserManage.registerParser(new Mp3Parser());
        System.out.println(ParserManage.getSong("MP3".getBytes()));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println(13);
        });
    }
}
