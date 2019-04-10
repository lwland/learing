package learn.lwl.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Date;

public class WebSocketServer {
    public void bind(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("one client connected ");
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(63356));
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }

    private class WebSocketHandler extends SimpleChannelInboundHandler<Object> {
        private WebSocketServerHandshaker handshake;
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object request) throws Exception {
            if (request instanceof FullHttpRequest) { handlerHttp(ctx, (FullHttpRequest) request); }
            if (request instanceof WebSocketFrame) { handlerWebSocket(ctx, (WebSocketFrame) request); }
        }
        private void handlerWebSocket(ChannelHandlerContext ctx, WebSocketFrame request) {
            if (request instanceof CloseWebSocketFrame) {
                handshake.close(ctx.channel(), (CloseWebSocketFrame) request.retain());
                return; }
            if (request instanceof PingWebSocketFrame) {
                ctx.channel().write(new PongWebSocketFrame(request.content()));
                return; }
            if (!(request instanceof TextWebSocketFrame)) {
                throw new UnsupportedOperationException("only support text webSocket message"); }
            String msg = ((TextWebSocketFrame) request).text();
            System.out.println("webSocket has receiver message " + msg);
            ctx.channel().write(new TextWebSocketFrame("欢迎使用Netty Socket服务,现在时刻是" + new Date().toString()));
        }
        private void handlerHttp(ChannelHandlerContext ctx, FullHttpRequest request) {
            WebSocketServerHandshakerFactory factory =
                    new WebSocketServerHandshakerFactory("ws://localhost:8080/webSocket", null, false);
            handshake = factory.newHandshaker(request);
            if (handshake == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshake.handshake(ctx.channel(), request); }
        }
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
        private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
            ctx.writeAndFlush(response);
        }

    }


    public static void main(String[] args) {
        new WebSocketServer().bind(8080);
    }
}
