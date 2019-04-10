package learn.lwl.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class KryoDecoder extends MessageToMessageDecoder<ByteBuf> {
    private Class aClass;
    private List<Class> classes;

    public KryoDecoder(Class aClass) {
        super();
        this.aClass = aClass;
    }

    public KryoDecoder(Class aClass, List<Class> classes) {
        super();
        this.classes = classes;
        this.aClass = aClass;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        byte[] raw = new byte[len];
        byteBuf.getBytes(byteBuf.readerIndex(), raw, 0, len);
        if (classes != null) {
            list.add(KryoUtil.deserialize(aClass,classes, raw));
        } else {
            list.add(KryoUtil.deserialize(aClass, raw));
        }

    }
}
