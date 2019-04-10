package learn.lwl.design.iterator;


import java.util.ArrayList;
import java.util.List;

public class BookShelf implements Aggregate {
    private List<Book> books = new ArrayList<>();
    private int last;

    public Book getAt(int index) {
        return books.get(index);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public void appendBook(Book book) {
        books.add(book);
        last++;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
