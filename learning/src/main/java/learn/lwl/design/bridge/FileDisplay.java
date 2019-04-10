package learn.lwl.design.bridge;

import java.io.*;

public class FileDisplay extends DisplayImpl {
    private String fileName;
    private BufferedReader reader;

    public FileDisplay(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void rawOpen() {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rawPrint() {
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.lines().forEach(System.out::println);
    }

    @Override
    public void rawClose() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
