package learn.lwl.design.adapter.question;

import java.io.*;
import java.util.Properties;

public class FileProperties implements FileIo {
    private Properties properties;

    public FileProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void readFromFile(String file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
    }

    @Override
    public void writeToFile(String file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        properties.store(outputStream, "write by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
