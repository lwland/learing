package learn.lwl.netty.netty.custom.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import learn.lwl.netty.netty.custom.message.CustomHeader;
import learn.lwl.netty.netty.custom.message.CustomMessage;

import java.util.List;
import java.util.Map;

public class CustomEncode extends MessageToMessageEncoder<CustomMessage> {
    private MarshallingEncoder marshallingEncoder;

    public CustomEncode(MarshallingEncoder marshallingEncoder) {
        this.marshallingEncoder = marshallingEncoder;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMessage msg, List<Object> list) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("the message is null");
        }
        ByteBuf sendBuf = Unpooled.buffer();
        CustomHeader header = msg.getHeader();
        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeLong(header.getSessionID());
        sendBuf.writeByte(header.getType());
        sendBuf.writeByte(header.getPriority());
        Map<String, Object> attachment = header.getAttachment();
        if (attachment != null) {
            sendBuf.writeInt(header.getAttachment().size());
            String key = null;
            byte[] keyArray = null;
            Object value = null;
            for (Map.Entry<String, Object> entry : attachment.entrySet()) {
                key = entry.getKey();
                keyArray = key.getBytes("UTF-8");
                sendBuf.writeInt(keyArray.length);
                sendBuf.writeBytes(keyArray);
                value = entry.getValue();
//                marshallingEncoder.
            }
//            sendBuf.writeBytes()
        }
//        NettyM

    }
}
