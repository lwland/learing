package learn.lwl.current.producer_custom;

public class PcData {
    private int id;
    private String name;

    public PcData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PcData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
