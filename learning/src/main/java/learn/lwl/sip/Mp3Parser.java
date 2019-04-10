package learn.lwl.sip;

import java.util.Arrays;

public class Mp3Parser implements Parser {
    private final byte[] FORMAT = "MP3".getBytes();
    private final int LENGTH = FORMAT.length;

    @Override
    public Song getSong(byte[] data) {
        if (!isDataCompatible(data)) {
            throw new SongNotFoundException("song not found use mp3 parser");
        }
        return new Song("《北京东路的日子》", "刘仟楚", "mp3", 220L);
    }

    private boolean isDataCompatible(byte[] data) {
        byte[] format = Arrays.copyOfRange(data, 0, FORMAT.length);
        return Arrays.equals(format, FORMAT);
    }
}
