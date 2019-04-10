package learn.lwl.test;

public class Fruit {
    protected String name;
    protected Integer size;

    public Fruit() {
    }

    public Fruit(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
