package learn.lwl.netty;

import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
public class UserInfo implements Serializable {
    private final long serialVersionUID = 1l;
    private String userName;
    private int userId;
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "serialVersionUID=" + serialVersionUID +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", age=" + age +
                '}';
    }
}
