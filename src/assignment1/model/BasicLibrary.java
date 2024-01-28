package assignment1.model;

public interface BasicLibrary {

    void addBook(Book book);
    void removeBook(Book book);
    void removeAll();
    void addAll(Book[] books);

}
