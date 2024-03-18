package assignment1.model;

import java.util.Collection;
import java.util.TreeSet;

public interface DataStore {
    void init();
    TreeSet<Book> getData();
    void writeIntoFile();
    void addBook(Book book);
    void addAllBooks(Book[] book);
    void addAllBooks(Collection<Book> book);
    void removeBook(Book book);
    void removeAllBooks();
    void removeAllBooks(Book[] books);
    void removeAllBooks(Collection<Book> books);
    void updateBook(Book book);

}
