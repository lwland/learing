package learn.lwl.netty.netty.custom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;
import learn.lwl.netty.netty.custom.message.MessageType;

public class LoginReqHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("send login request");
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("receive response from " + msg.toString());
        CustomMessage message = (CustomMessage) msg;
        if (message.getHeader() != null && message
                .getHeader().getType() == MessageType.LOGIN_RESP) {
            if ((byte) (message.getBody()) != 0) {
                ctx.close();
            } else {
                System.out.println("login is ok");
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    private CustomMessage buildLoginReq() {
        CustomMessage customMessage = new CustomMessage();
        CustomHeader header = new CustomHeader();
        header.setType(MessageType.LOGIN_REQ);
        customMessage.setHeader(header);
        customMessage.setHeader(header);
        return customMessage;
    }
}
