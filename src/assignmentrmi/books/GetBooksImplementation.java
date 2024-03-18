package assignmentrmi.books;

import assignment1.model.Book;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GetBooksImplementation extends UnicastRemoteObject implements GetBooksService {
    Connection connection;
    TreeSet<Book> data = new TreeSet<>();
    private static final String ALLBOOKSQUERY = "SELECT * FROM BOOKS";
    private static final String GETAUTHORS = "SELECT a.authorName FROM BOOK_AUTHORS b,AUTHORS a where a.id = b.author_id and b.book_id = ?";

    public GetBooksImplementation() throws RemoteException {
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3301/test?characterEncoding=UTF-8", "root", "");
            Statement statement = connection.createStatement();
            ResultSet allBooks = statement.executeQuery(ALLBOOKSQUERY);
            System.out.println("HI");
            PreparedStatement getAuthors = connection.prepareStatement(GETAUTHORS);
            while(allBooks.next()){
                Book book = new Book(allBooks.getInt(Book.BOOKID));
                getAuthors.setInt(1,book.getBookId());
                ResultSet bookAuthors = getAuthors.executeQuery();
                ArrayList<String> a = new ArrayList<>();
                while(bookAuthors.next()){
                    a.add(bookAuthors.getString(1));
                }
                book.setBookName(allBooks.getString(Book.BOOKNAME));
                book.setAuthorNames(a);
                book.setPrice(allBooks.getString(Book.PRICE));
                book.setPublication(allBooks.getString(Book.PUBLICATION));
                book.set(Book.DATEOFPUBLICATION,convertDate(allBooks.getString(Book.DATEOFPUBLICATION)));
                book.setDescription(allBooks.getString(Book.DESCRIPTION));
                data.add(book);
                System.out.println(book);
            }

            allBooks.close();
            statement.close();
            getAuthors.close();
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
    }

    @Override
    public List<Book> getBooks() throws RemoteException {
        try {
            data.clear();
            Statement statement = connection.createStatement();
            ResultSet allBooks = statement.executeQuery(ALLBOOKSQUERY);
            System.out.println("HI");
            PreparedStatement getAuthors = connection.prepareStatement(GETAUTHORS);
            while(allBooks.next()){
                Book book = new Book(allBooks.getInt(Book.BOOKID));
                getAuthors.setInt(1,book.getBookId());
                ResultSet bookAuthors = getAuthors.executeQuery();
                ArrayList<String> a = new ArrayList<>();
                while(bookAuthors.next()){
                    a.add(bookAuthors.getString(1));
                }
                book.setBookName(allBooks.getString(Book.BOOKNAME));
                book.setAuthorNames(a);
                book.setPrice(allBooks.getString(Book.PRICE));
                book.setPublication(allBooks.getString(Book.PUBLICATION));
                book.set(Book.DATEOFPUBLICATION,convertDate(allBooks.getString(Book.DATEOFPUBLICATION)));
                book.setDescription(allBooks.getString(Book.DESCRIPTION));
                data.add(book);
                System.out.println(book);
            }

            allBooks.close();
            statement.close();
            getAuthors.close();
        }catch (Exception e){

        }
        // TODO Auto-generated method stub
        return data.stream().toList();
    }

    static String convertDate(String date){
        String[] dateParse = date.split("-");
        String newDate = dateParse[2]+"-"+dateParse[1]+"-"+dateParse[0];
        return newDate;
    }
}
