package learn.lwl.netty.netty.custom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;
import learn.lwl.netty.netty.custom.message.MessageType;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CustomMessage message = (CustomMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEART_BEAT_RESP) {
            System.out.println("heard beat has receive response");
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            System.out.println("heart beat is to sent message to server");
            CustomMessage message = bulidHeartBeatRequest();
            ctx.writeAndFlush(message);

        }

        private CustomMessage bulidHeartBeatRequest() {
            CustomMessage message = new CustomMessage();
            CustomHeader header = new CustomHeader();
            header.setType(MessageType.HEART_BEAT_REQ);
            message.setHeader(header);
            return message;
        }

    }
}
