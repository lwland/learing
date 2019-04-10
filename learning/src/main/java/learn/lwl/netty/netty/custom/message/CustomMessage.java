package learn.lwl.netty.netty.custom.message;

public final class CustomMessage {
    private CustomHeader header;
    private Object body;

    public CustomHeader getHeader() {
        return header;
    }

    public void setHeader(CustomHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
