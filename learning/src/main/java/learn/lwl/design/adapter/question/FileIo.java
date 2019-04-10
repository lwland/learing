package learn.lwl.design.adapter.question;

import java.io.IOException;

public interface FileIo {
    public void readFromFile(String file) throws IOException;

    public void writeToFile(String file) throws IOException;

    public void setValue(String key, String value) throws IOException;

    public String getValue(String key) throws IOException;
}
