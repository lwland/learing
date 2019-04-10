package learn.lwl.netty;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public Client() {
    }

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(10030);
        userInfo.setUserName("10030");
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteOutputStream);

            objectOutputStream.writeObject(userInfo);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = byteOutputStream.getBytes();
        System.out.print(bytes.length);
        List<UserInfo> list = new ArrayList<>();
        UserInfo user1 = new UserInfo();
        user1.setUserId(10031);
        user1.setUserName("10031");
        UserInfo user2 = new UserInfo();
        user2.setUserId(10032);
        user2.setUserName("10032");
        UserInfo user3 = new UserInfo();
        user3.setUserId(10033);
        user3.setUserName("10033");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        MessagePack pack = new MessagePack();
        byte[] raw = null;
        try {
            raw = pack.write(list);
            System.out.println(raw.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Schema<UserInfo> schema = RuntimeSchema.getSchema(UserInfo.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        final byte[] protoStuff;
        try {
            protoStuff = ProtobufIOUtil.toByteArray(user1, schema, buffer);
        } finally {
            buffer.clear();
        }
        UserInfo parsed = schema.newMessage();
        ProtobufIOUtil.mergeFrom(protoStuff, parsed, schema);
    }
}
