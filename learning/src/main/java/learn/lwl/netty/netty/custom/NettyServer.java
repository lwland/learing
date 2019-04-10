package learn.lwl.netty.netty.custom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;
import learn.lwl.netty.netty.KryoDecoder;
import learn.lwl.netty.netty.KryoEncoder;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;

import java.util.ArrayList;
import java.util.List;

public class NettyServer {
    public void bind(int port) {
        EventLoopGroup work = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            List<Class> list = new ArrayList<>();
                            list.add(CustomMessage.class);
                            list.add(CustomHeader.class);
                            socketChannel.pipeline().addLast("kryo_Decoder", new KryoDecoder(CustomMessage.class, list));
                            socketChannel.pipeline().addLast("kryo_Encoder", new KryoEncoder(CustomMessage.class, list));
                           socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 4, 4));
                            socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
                            socketChannel.pipeline().addLast(new ReadTimeoutHandler(50));
                            socketChannel.pipeline().addLast(new LoginRespHandler());
                            socketChannel.pipeline().addLast(new HeartBeatRespHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new NettyServer().bind(8080);
    }

}
