package learn.lwl.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;

public class TimeServer {
    public void bind(int port) throws InterruptedException {
        //配置服务端的NIO线程组
        //boss线程组用于处理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //work线程组用于读写
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //netty的服务端启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)//设置创建channel的类
                    .option(ChannelOption.SO_BACKLOG, 100)//设置Tcp参数
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            System.out.println("one channel connect");
                            ByteBuf delimit = Unpooled.copiedBuffer("$_".getBytes());
//            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimit));
//            channel.pipeline().addLast(new FixedLengthFrameDecoder(20));
//                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(MarshallingCodecFactory.buildDecode());
                            channel.pipeline().addLast(MarshallingCodecFactory.buildeEncode());
                            channel.pipeline().addLast(new TimeServerHandler());

                        }
                    });//设置处理类
            //bind端口同时启动线程
            ChannelFuture future = bootstrap.bind(port).sync();//bind端口
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();//异步的通知回调
        } finally {
            //优雅退出，关闭资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    private class TimeServerHandler extends ChannelInboundHandlerAdapter {
        private int count = 1;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("read from client");
            String body = (String) msg;
            System.out.println(count++ + " the server has get request body " + body);
            String currentTime = "time".equals(body) ? LocalDateTime.now().toString() : "BAD REQUEST";
            currentTime += "$_";//System.getProperty("line.separator");
            ByteBuf writeBuf = Unpooled.copiedBuffer(currentTime.getBytes());
            ChannelFuture future = ctx.writeAndFlush(writeBuf);
//            future.addListener(ChannelFutureListener.CLOSE);
            System.out.println("write to client" + currentTime);
            ReferenceCountUtil.release(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        try {
            new TimeServer().bind(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
