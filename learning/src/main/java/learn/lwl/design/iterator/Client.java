package learn.lwl.design.iterator;

public class Client {
    //迭代器模式的优点是可以将迭代和实现分离，无论BookShelf使用list、array还是其他数据结构存储书籍，都不会影响迭代
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.appendBook(new Book("java核心技术"));
        bookShelf.appendBook(new Book("设计模式"));
        bookShelf.appendBook(new Book("数据结构"));
        Iterator iterator = new BookShelfIterator(bookShelf);
        iterator.first();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }
        iterator.last();
        while (iterator.hasPrevious()) {
            Book book = (Book) iterator.previous();
            System.out.println(book.getName());
        }
    }
}
