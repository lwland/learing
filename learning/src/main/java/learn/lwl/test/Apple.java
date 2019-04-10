package learn.lwl.test;

public class Apple extends Fruit implements IFruit {
    private String type;

    public Apple() {

    }

    public Apple(String name, Integer size, String type) {
        super(name, size);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
