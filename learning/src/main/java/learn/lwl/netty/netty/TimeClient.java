package learn.lwl.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {
    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();//处理连接
        try {
            Bootstrap bootstrap = new Bootstrap();//客户端辅助类
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .group(group)
                    .handler(new ChannelInitializer<SocketChannel>() {//初始化处理
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf delimit = Unpooled.copiedBuffer("$_".getBytes());
//                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimit));
//                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(MarshallingCodecFactory.buildDecode());
                            socketChannel.pipeline().addLast(MarshallingCodecFactory.buildeEncode());
                            socketChannel.pipeline().addLast(new TimeClientHandle());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();//连接服务器
            future.channel().closeFuture().sync();//关闭连接
        } finally {
            group.shutdownGracefully();
        }

    }

    public class TimeClientHandle extends ChannelInboundHandlerAdapter {
        public TimeClientHandle() {
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            ByteBuf message = null;
//            String order = "time" + "$_";
//            message = Unpooled.buffer(order.getBytes().length);
//            message.writeBytes(order.getBytes());
            ctx.writeAndFlush("hello world");
            System.out.println("write to server");
//            System.out.print(order);
//            for (int i = 0; i < 100; i++) {
//                message = Unpooled.buffer(order.getBytes().length);
//                message.writeBytes(order.getBytes());
//                ctx.writeAndFlush(message);
//                System.out.println("write to server");
//            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("read from server");
//            ByteBuf buf = (ByteBuf) msg;
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.readBytes(bytes);
//            String body = new String(bytes, "utf-8");
            System.out.println(msg);
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) {
        try {
            new TimeClient().connect(8080, "127.0.0.1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
