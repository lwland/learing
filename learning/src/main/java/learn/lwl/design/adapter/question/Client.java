package learn.lwl.design.adapter.question;

import java.io.IOException;
import java.util.Properties;

public class Client {
    public static void main(String[] args) throws IOException {
        String inFile = "file.txt";
        String outFile = "out.txt";
        Properties properties = new Properties();
        FileIo fileIo = new FileProperties(properties);
        fileIo.readFromFile(inFile);
        System.out.println(fileIo.getValue("name"));
        fileIo.setValue("name","jjj");
        fileIo.setValue("email", "wenleili@sohu-inc.com");
        fileIo.setValue("age", "25");
        fileIo.setValue("address", "beijing");
        fileIo.writeToFile(outFile);
    }
}
