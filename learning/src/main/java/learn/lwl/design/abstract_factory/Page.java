package learn.lwl.design.abstract_factory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Page {
    protected String title;
    protected String author;

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    protected List<Item> content = new ArrayList<>();

    public void add(Item item) {
        content.add(item);
    }

    public void outPut() {
        String fileName = title + ".html";
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
            writer.write(this.makeHtml());
            System.out.println("out 完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String makeHtml();
}
