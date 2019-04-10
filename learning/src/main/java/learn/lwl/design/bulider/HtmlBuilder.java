package learn.lwl.design.bulider;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlBuilder extends Builder {
    private String fileName;
    private PrintWriter printWriter;

    public HtmlBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void makeTitle(String s) {
        fileName = fileName + ".html";
        try {
            printWriter = new PrintWriter(new FileWriter(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.write("<html><head><title>" + s + "</title></head></html><body>");
        printWriter.write("<h1>" + s + "</h1>");
    }

    @Override
    public void makeString(String s) {
        printWriter.write("<p>" + s + "</p>");

    }

    @Override
    public void makeItems(String[] items) {
        printWriter.write("<ul>");
        for (String item : items) {
            printWriter.write("<li>" + item + "</li>");
        }
        printWriter.write("</ul>");
    }

    @Override
    public void close() {
        printWriter.write("</body></html>");
        printWriter.close();
    }

    public String getResult() {
        return fileName;
    }
}
