package assignment1.model;

import java.util.*;

public interface BasicLibrary {

    void addBook(Book book);
    void addAllBooks(Book[] books);
    void addAllBooks(Collection<Book> books);
    void removeBook(Book book);
    void removeAllBooks();
    void removeAllBooks(Book[] books);
    void removeAllBooks(Collection<Book> books);
    List<Book> getAllBooks();

    Iterator<Book> getIterator();
    TreeSet<Book> getLibrary();
    List<Book> filterBy(HashMap<String,String> queries);


}
