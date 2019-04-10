package learn.lwl.design.abstract_factory;

import java.util.ArrayList;
import java.util.List;

public abstract class Tray extends Item {
    protected List<Item> trays = new ArrayList<>();

    public Tray(String caption) {
        super(caption);
    }

    public void add(Item item) {
        trays.add(item);
    }
}
