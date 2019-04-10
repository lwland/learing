package learn.lwl.netty.netty;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtoStuffUtil {
    private static Map<Class<?>, Schema<?>> schemaMap = new ConcurrentHashMap<>();

    public static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaMap.get(clazz);
        if (schema != null) {
            return schema;
        } else {
            return RuntimeSchema.getSchema(clazz);
        }
    }

    public static <T> byte[] serialize(T obj) {
        Class clazz = obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtostuffIOUtil.toByteArray(obj, getSchema(clazz), buffer);
    }

    public static <T> T deserialize(byte[] raw, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T message = clazz.newInstance();
        ProtostuffIOUtil.mergeFrom(raw, message, getSchema(clazz));
        return message;
    }
}
