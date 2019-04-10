package learn.lwl.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class KryoEncoder extends MessageToByteEncoder {
    private Class aClass;
    private List<Class> classes;

    public KryoEncoder(Class aClass) {
        super();
        this.aClass = aClass;
    }

    public KryoEncoder(Class aClass, List<Class> classes) {
        super();
        this.classes = classes;
        this.aClass = aClass;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byte[] raw;
        if (classes != null) {
            raw = KryoUtil.serialize(aClass, classes, o);
        } else {
            raw = KryoUtil.serialize(aClass, o);
        }

        byteBuf.writeBytes(raw);
    }
}
