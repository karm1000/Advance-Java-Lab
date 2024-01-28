package assignment1.model;

import java.io.Serializable;
import java.util.*;

public class BooksLibrary implements Serializable,BasicLibrary {
    HashSet<Book> library;
    public BooksLibrary(){
        library = new HashSet<>();
    }
    public BooksLibrary(HashSet<Book> library){
        this.library = library;
    }

    @Override
    public void addBook(Book book){
        library.add(book);
    }

    @Override
    public void removeBook(Book book){
        library.remove(book);
    }

    @Override
    public void removeAll(){
        library.clear();
    }

    @Override
    public void addAll(Book[] books){
        library.addAll(Arrays.stream(books).toList());
    }

    public List<Book> getAllBooks(){
        return library.stream().toList();
    }

    public Iterator<Book> getIterator(){
        return library.iterator();
    }

    @Override
    public String toString() {
        return "BooksLibrary{" +
                "library=" + library +
                '}';
    }

    public HashSet<Book> getLibrary() {
        return library;
    }
}
