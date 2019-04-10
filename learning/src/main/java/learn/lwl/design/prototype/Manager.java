package learn.lwl.design.prototype;

import java.util.HashMap;
import java.util.Map;

public class Manager {
    private Map<String, Product> showCase = new HashMap();

    public void register(Product product, String name) {
        showCase.put(name, product);
    }

    public Product create(String name) {
        Product product = (Product) showCase.get(name);
        return product.createClone();
    }
}
