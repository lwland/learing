package learn.lwl.netty.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsynchronousClientHandler implements CompletionHandler<Void, AsynchronousClientHandler>, Runnable {
    private int port;
    private String host;
    private AsynchronousSocketChannel socketChannel;
    private CountDownLatch countDownLatch;

    public AsynchronousClientHandler(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            socketChannel = AsynchronousSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        socketChannel.connect(new InetSocketAddress(host, port), this, this);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void completed(Void result, AsynchronousClientHandler attachment) {
        String order = "time";
        byte[] bytes = order.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        socketChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    socketChannel.write(buffer, buffer, this);
                } else {
                    ByteBuffer read = ByteBuffer.allocate(1024);
                    socketChannel.read(read, read, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] response = new byte[attachment.remaining()];
                            attachment.get(response);
                            try {
                                String body = new String(response, "utf-8");
                                System.out.println("now is " + body);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            countDownLatch.countDown();
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                socketChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    socketChannel.close();
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void failed(Throwable exc, AsynchronousClientHandler attachment) {
        try {
            socketChannel.close();
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
