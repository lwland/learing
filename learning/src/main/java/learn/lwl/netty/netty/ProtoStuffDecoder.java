package learn.lwl.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ProtoStuffDecoder extends MessageToMessageDecoder<ByteBuf> {
    private Class aClass;

    public ProtoStuffDecoder(Class aClass) {
        super();
        this.aClass = aClass;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        byte[] bytes = new byte[len];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, len);
        list.add(ProtoStuffUtil.deserialize(bytes, aClass));
    }
}
