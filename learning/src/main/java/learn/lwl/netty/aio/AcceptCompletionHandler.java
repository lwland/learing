package learn.lwl.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsynTimeServerHandler> {


    @Override
    public void completed(AsynchronousSocketChannel result, AsynTimeServerHandler attachment) {
        System.out.println("server has got one accept");
        attachment.asynchronousServerSocketChannel.accept(attachment, this);//继续监听
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsynTimeServerHandler attachment) {
        attachment.countDownLatch.countDown();
    }
}

