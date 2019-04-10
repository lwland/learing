package learn.lwl.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;

import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    public void bind() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        public void initChannel(Channel channel) throws Exception {
                            System.out.println("one client connect");
//                            channel.pipeline().addLast(new FixedLengthFrameDecoder(20));
//                            channel.pipeline().addLast(new StringEncoder());
//                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast("framDecoder", new LengthFieldBasedFrameDecoder(63535, 0, 2, 0, 2));
                            channel.pipeline().addLast("framEncode", new LengthFieldPrepender(2));
//                            channel.pipeline().addLast("msgpack decoder", new MsgPackDecoder());
//                            channel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
//                            channel.pipeline().addLast("protostuff_Decoder", new ProtoStuffDecoder(UserInfo.class));
//                            channel.pipeline().addLast("protostuff_Encoder", new ProtoStuffEncoder());
                            List<Class> list = new ArrayList<>();
                            list.add(CustomMessage.class);
                            list.add(CustomHeader.class);
                            channel.pipeline().addLast("kryo_Decoder", new KryoDecoder(CustomMessage.class, list));
                            channel.pipeline().addLast("kryo_Encoder", new KryoEncoder(CustomMessage.class, list));
//                            channel.pipeline().addLast(MarshallingCodecFactory.buildDecode());
//                            channel.pipeline().addLast(MarshallingCodecFactory.buildeEncode());
                            channel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class EchoServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("receive msg from client:" + msg);
//            System.out.print("the server receive msg from client:" + msg);
            ctx.writeAndFlush(msg);
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
        new EchoServer().bind();
    }
}
