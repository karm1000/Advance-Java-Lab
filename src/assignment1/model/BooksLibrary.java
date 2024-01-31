package assignment1.model;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class BooksLibrary extends FileIO implements Serializable {
    public BooksLibrary() throws IOException {
        super();
    }

//    public BooksLibrary(TreeSet<Book> library) throws IOException {
//        super(library);
//    }

    @Override
    public void add(Book book) {
        data.add(book);
    }

    @Override
    public void addAll(Book[] books) {
        data.addAll(Arrays.stream(books).toList());
    }

    public void addAll(Collection<Book> books){
        data.addAll(books);
    }


    @Override
    public void remove(Book book) {
        data.remove(book);
    }

    @Override
    public void removeAll() {
        data.clear();
    }

    @Override
    public void removeAll(Book[] books) {
        data.removeAll(Arrays.stream(books).toList());
    }

    @Override
    public void removeAll(Collection<Book> books) {
        data.removeAll(books);
    }

    @Override
    public List<Book> getAllBooks(){
        return data.stream().toList();
    }

    @Override
    public Iterator<Book> getIterator(){
        return data.iterator();
    }

    @Override
    public TreeSet<Book> getLibrary() {
        return data;
    }
    @Override
    public String toString() {
        return "BooksLibrary{" +
                "library=" + data +
                '}';
    }
}
