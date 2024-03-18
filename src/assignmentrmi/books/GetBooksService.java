package assignmentrmi.books;

import assignment1.model.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface GetBooksService extends Remote{
    List<Book> getBooks() throws RemoteException;
} 