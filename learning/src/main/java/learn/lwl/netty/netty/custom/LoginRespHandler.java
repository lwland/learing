package learn.lwl.netty.netty.custom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;
import learn.lwl.netty.netty.custom.message.MessageType;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginRespHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] whiteIp = {"127.0.0.1", "19.168.0.1"};


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CustomMessage message = (CustomMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_REQ) {
            //
            String node = ctx.channel().remoteAddress().toString();
            CustomMessage respMessage = null;
            if (nodeCheck.containsKey(node)) {//重复登录
                respMessage = buildeResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOk = false;
                for (String wip : whiteIp) {
                    if (wip.equals(ip)) {
                        isOk = true;
                        break;
                    }
                }
                if (isOk) {
                    nodeCheck.put(node, true);
                    respMessage = buildeResponse((byte) 0);
                } else {
                    respMessage = buildeResponse((byte) -1);
                }
            }
            System.out.println("the login response is " + respMessage.getBody());
            ctx.writeAndFlush(respMessage);
        } else {
            ctx.fireChannelRead(msg);//透传
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);

    }

    private CustomMessage buildeResponse(byte value) {
        CustomMessage message = new CustomMessage();
        CustomHeader header = new CustomHeader();
        header.setType(MessageType.LOGIN_RESP);
        message.setBody(value);
        message.setHeader(header);
        return message;
    }
}
