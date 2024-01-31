package assignment1.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public interface BasicLibrary {

    void add(Book book);
    void addAll(Book[] books);
    void addAll(Collection<Book> books);
    void remove(Book book);
    void removeAll();
    void removeAll(Book[] books);
    void removeAll(Collection<Book> books);
    List<Book> getAllBooks();

    Iterator<Book> getIterator();
    TreeSet<Book> getLibrary();


}
