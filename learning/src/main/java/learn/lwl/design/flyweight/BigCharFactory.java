package learn.lwl.design.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BigCharFactory {
    private static BigCharFactory singleton = new BigCharFactory();

    private Map<String, BigChar> pool = new HashMap<>();

    public static BigCharFactory getSingleton() {
        return singleton;
    }

    public synchronized BigChar getBigChar(char charName) {
        return pool.get(charName);
    }


}
