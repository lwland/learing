package learn.lwl.design.Singleton.question;

import java.util.HashMap;
import java.util.Map;

public class Triple {
    private static Map<Integer, Triple> map = null;

    static {
        map = new HashMap<>();
        map.put(0, new Triple());
        map.put(1, new Triple());
        map.put(2, new Triple());
    }

    private Triple() {

    }

    public static final Triple getInstance(Integer id) {
        return map.getOrDefault(id, null);
    }
}
