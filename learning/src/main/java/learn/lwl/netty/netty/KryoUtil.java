package learn.lwl.netty.netty;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.List;

public class KryoUtil {
    public static <T> byte[] serialize(Class<T> tClass, T obj) {
        Kryo kryo = new Kryo();
        kryo.register(tClass);
        Output output = new Output(1024);
        kryo.writeObject(output, obj);
        byte[] raw = output.getBuffer();
        output.close();
        return raw;
    }

    public static <T> T deserialize(Class<T> tClass, byte[] raw) {
        Kryo kryo = new Kryo();
        kryo.register(tClass);
        Input input = new Input(raw);
        T obj = kryo.readObject(input, tClass);
        input.close();
        return obj;
    }

    public static <T> byte[] serialize(Class<T> tClass, List<Class> tClasss, T obj) {
        Kryo kryo = new Kryo();
        for (Class c : tClasss) {
            kryo.register(c);
        }

        Output output = new Output(1024);
        kryo.writeObject(output, obj);
        byte[] raw = output.getBuffer();
        output.close();
        return raw;
    }

    public static <T> T deserialize(Class<T> tClass, List<Class> tClasss, byte[] raw) {
        Kryo kryo = new Kryo();
        for (Class c : tClasss) {
            kryo.register(c);
        }
        Input input = new Input(raw);
        T obj = kryo.readObject(input, tClass);
        input.close();
        return obj;
    }
}
