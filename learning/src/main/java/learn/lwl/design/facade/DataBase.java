package learn.lwl.design.facade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBase {
    public DataBase() {
    }
    public static Properties getProperties(String dname){
        String fileName=dname+".txt";
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
