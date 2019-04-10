package learn.lwl.design.iterator;

public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator() {
    }

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public Boolean hasNext() {
        return index < bookShelf.getLast() ? true : false;
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBooks().get(index);
        index++;
        return book;

    }

    @Override
    public Boolean hasPrevious() {
        return index >= 0 ? true : false;
    }

    @Override
    public Object previous() {
        Book book = bookShelf.getBooks().get(index);
        index--;
        return book;
    }

    @Override
    public void last() {
        index = bookShelf.getLast() - 1;
    }

    @Override
    public void first() {
        index = 0;
    }
}
