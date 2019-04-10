package learn.lwl.sip;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParserManage {
    private final static CopyOnWriteArrayList<ParserInfo> registeredParsers = new CopyOnWriteArrayList<>();

    static {
//        loadInitialParsers();
        System.out.println("songParser initialized");
    }

    private static void loadInitialParsers() {
        ServiceLoader<Parser> loader = ServiceLoader.load(Parser.class);
        Iterator<Parser> iterator = loader.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    public synchronized static void registerParser(Parser parse) {
        registeredParsers.add(new ParserInfo(parse));
    }

    public static Song getSong(byte[] data) {
        for (ParserInfo parserInfo : registeredParsers) {
            Song song = parserInfo.parser.getSong(data);
            if (song != null) {
                return song;
            }
        }
        throw new SongNotFoundException("song not found");
    }
}
