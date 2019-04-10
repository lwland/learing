package learn.lwl.netty.netty.custom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;
import learn.lwl.netty.netty.KryoDecoder;
import learn.lwl.netty.netty.KryoEncoder;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class NettyClient {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void connect(String host, int port) {
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class)
                    .group(work)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            List<Class> list = new ArrayList<>();
                            list.add(CustomMessage.class);
                            list.add(CustomHeader.class);
                            socketChannel.pipeline().addLast("kryo_Decoder", new KryoDecoder(CustomMessage.class, list));
                            socketChannel.pipeline().addLast("kryo_Encoder", new KryoEncoder(CustomMessage.class, list));
//
                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 4, 4));
                            socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
                            socketChannel.pipeline().addLast(new ReadTimeoutHandler(50));
                            socketChannel.pipeline().addLast(new LoginReqHandler());
                            socketChannel.pipeline().addLast(new HeartBeatReqHandler());
//                        socketChannel.pipeline().addLast(new );
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    connect(host, port);//断线重连
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 8080);
    }

}
