package learn.lwl.design.factory;

import java.util.HashMap;
import java.util.Map;

public class IDCardFactory extends Factory {
    private Map<Integer, String> owners;

    public IDCardFactory() {
        owners = new HashMap<>();
    }

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        Integer id = owners.size() + 1;
        owners.put(id, ((IDCard) product).getOwner());
    }
}
