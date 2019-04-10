package learn.lwl.sip;

public class Song {
    private String name;
    private String author;
    private String format;
    private long time;

    public Song() {
    }

    public Song(String name, String author, String format, long time) {
        this.name = name;
        this.author = author;
        this.format = format;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", format='" + format + '\'' +
                ", time=" + time +
                '}';
    }
}
