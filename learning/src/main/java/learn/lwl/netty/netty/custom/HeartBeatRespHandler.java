package learn.lwl.netty.netty.custom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;
import learn.lwl.netty.netty.custom.message.MessageType;

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CustomMessage message = (CustomMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEART_BEAT_REQ) {
            System.out.println("receiver heart beat from server");
            CustomMessage resp = buildHeartBeatResp();
            ctx.writeAndFlush(resp);
            ctx.fireChannelRead(msg);
        }
    }

    private CustomMessage buildHeartBeatResp() {
        CustomMessage message = new CustomMessage();
        CustomHeader header = new CustomHeader();
        header.setType(MessageType.HEART_BEAT_RESP);
        message.setHeader(header);
        return message;
    }


}
