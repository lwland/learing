package learn.lwl.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;
import learn.lwl.netty.netty.custom.message.MessageType;

import java.util.ArrayList;
import java.util.List;


public class EchoClient {
    public void run() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("framDecoder", new LengthFieldBasedFrameDecoder(63535, 0, 2, 0, 2));
                            socketChannel.pipeline().addLast("framEncode", new LengthFieldPrepender(2));
//                            socketChannel.pipeline().addLast("msgpack decoder", new MsgPackDecoder());
//                            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
//                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
//                            socketChannel.pipeline().addLast(new StringDecoder());
//                            socketChannel.pipeline().addLast(new StringEncoder());
//                            socketChannel.pipeline().addLast("protostuff_Decoder", new ProtoStuffDecoder(UserInfo.class));
//                            socketChannel.pipeline().addLast("protostuff_Encoder", new ProtoStuffEncoder());
                            List<Class> list = new ArrayList<>();
                            list.add(CustomMessage.class);
                            list.add(CustomHeader.class);
                            socketChannel.pipeline().addLast("kryo_Decoder", new KryoDecoder(CustomMessage.class, list));
                            socketChannel.pipeline().addLast("kryo_Encoder", new KryoEncoder(CustomMessage.class, list));
//                            socketChannel.pipeline().addLast(MarshallingCodecFactory.buildDecode());
//                            socketChannel.pipeline().addLast(MarshallingCodecFactory.buildeEncode());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new EchoClient().run();
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("one channel");

//            List<UserInfo> userInfos = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                UserInfo userInfo = new UserInfo();
//                userInfo.setUserName("lwl" + i);
//                userInfo.setUserId(1000 + i);
//                userInfo.setAge(i + 10);
//                userInfos.add(userInfo);
//            }
//            byte[] bytes = "hello client".getBytes();
//            ByteBuf message = Unpooled.buffer(bytes.length);
//            message.writeBytes(bytes);
//            for (UserInfo userInfo : userInfos) {
//                ctx.writeAndFlush(userInfo);
//            }
            CustomMessage message = new CustomMessage();
            CustomHeader header = new CustomHeader();
            header.setType(MessageType.HEART_BEAT_RESP);
            header.setLength(1000);
            message.setHeader(header);
            ctx.writeAndFlush(message);
            System.out.println("write to server msg=");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client receive message from server:" + msg);
//            ctx.writeAndFlush(msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

}
