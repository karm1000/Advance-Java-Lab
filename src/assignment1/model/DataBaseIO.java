package assignment1.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class DataBaseIO implements DataStore {
    TreeSet<Book> data = new TreeSet<>();
    Connection con;
    private static final String ALLBOOKSQUERY = "SELECT * FROM BOOKS";
    private static final String GETAUTHORS = "SELECT a.authorName FROM BOOK_AUTHORS b,AUTHORS a where a.id = b.author_id and b.book_id = ?";

    private static final String INSERTBOOK = "INSERT INTO BOOKS (BOOKNAME, PUBLICATION, DATEOFPUBLICATION, DESCRIPTION, PRICE) VALUES (?,?,?,?,?)";
    private static final String INSERTAUTHOR = "INSERT INTO AUTHORS (AUTHORNAME) VALUES (?)";
    private static final String INSERTBOOKAUTHOR = "INSERT INTO BOOK_AUTHORS (BOOK_ID,AUTHOR_ID) VALUES (?,?)";
    private static final String DELETEBOOK = "DELETE FROM BOOKS WHERE bookId = ?";
    private static final String DELETEBOOKAUTHOR = "DELETE FROM BOOK_AUTHORS WHERE BOOK_ID = ?";
    private static final String UPDATEBOOK = "UPDATE BOOKS SET bookName = ?, publication = ?, dateOfPublication = ?, description = ?, price = ? WHERE BOOKID = ?";

    public DataBaseIO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3301/test?characterEncoding=UTF-8", "root", "");
            Statement statement = con.createStatement();
//            statement.executeUpdate()
            ResultSet allBooks = statement.executeQuery(ALLBOOKSQUERY);
            System.out.println("HI");
            PreparedStatement getAuthors = con.prepareStatement(GETAUTHORS);
            while (allBooks.next()) {
                Book book = new Book(allBooks.getInt(Book.BOOKID));
                getAuthors.setInt(1, book.getBookId());
                ResultSet bookAuthors = getAuthors.executeQuery();
                ArrayList<String> a = new ArrayList<>();
                while (bookAuthors.next()) {
                    a.add(bookAuthors.getString(1));
                }
                book.setBookName(allBooks.getString(Book.BOOKNAME));
                book.setAuthorNames(a);
                book.setPrice(allBooks.getString(Book.PRICE));
                book.setPublication(allBooks.getString(Book.PUBLICATION));
                book.set(Book.DATEOFPUBLICATION, convertDate(allBooks.getString(Book.DATEOFPUBLICATION)));
                book.setDescription(allBooks.getString(Book.DESCRIPTION));
                data.add(book);
                System.out.println(book);
            }

            allBooks.close();
            statement.close();
            getAuthors.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public TreeSet<Book> getData() {
        return data;
    }

    @Override
    public void writeIntoFile() {

    }

    @Override
    public void addBook(Book book) {
        try {
//            "INSERT INTO BOOKS (BOOKNAME, PUBLICATION, DATEOFPUBLICATION, DESCRIPTION, PRICE) VALUES (?,?,?,?,?)"
            String[] dateParse = book.getDateOfPublication(true).split("-");
            String sqlType = dateParse[2] + "-" + dateParse[1] + "-" + dateParse[0];
            PreparedStatement statement = con.prepareStatement(INSERTBOOK);
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getPublication());
            statement.setDate(3, java.sql.Date.valueOf(sqlType));
            statement.setString(4, book.getDescription());
            statement.setDouble(5, book.getPrice());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    book.setBookId(resultSet.getInt(1));
                }
                PreparedStatement insertAuthor = con.prepareStatement(INSERTAUTHOR);
                PreparedStatement authorBook = con.prepareStatement(INSERTBOOKAUTHOR);
                PreparedStatement getAuthor = con.prepareStatement("SELECT id FROM AUTHORS WHERE AUTHORNAME = ?");
                for (String author : book.getAuthorNames()) {
                    getAuthor.setString(1, author.toUpperCase());
                    ResultSet a1 = getAuthor.executeQuery();
                    int id = 0;
                    if (a1.next()) {
                        id = a1.getInt("id");
                    } else {
                        insertAuthor.setString(1, author.toUpperCase());
                        insertAuthor.executeUpdate();
                        ResultSet authorSet = insertAuthor.getGeneratedKeys();
                        authorSet.next();
                        id = authorSet.getInt(1);
                    }
                    authorBook.setInt(1, book.getBookId());
                    authorBook.setInt(2, id);
                    authorBook.executeUpdate();

                }
                authorBook.close();
                insertAuthor.close();
                getAuthor.close();
            }
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addAllBooks(Book[] book) {

    }

    @Override
    public void addAllBooks(Collection<Book> book) {

    }

    @Override
    public void removeBook(Book book) {
        try {
            PreparedStatement deleteStatement = con.prepareStatement(DELETEBOOK);
            PreparedStatement deleteBookAuthor = con.prepareStatement(DELETEBOOKAUTHOR);
            deleteStatement.setInt(1, book.getBookId());
            deleteBookAuthor.setInt(1, book.getBookId());
            int rows = deleteStatement.executeUpdate();
            int r1 = deleteBookAuthor.executeUpdate();
            if (rows > 0) {
                System.out.println(book + "DELETED");
            }
            if (r1 > 0) {
                System.out.println(book + "AUTHOR DELETED");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void removeAllBooks() {

    }

    @Override
    public void removeAllBooks(Book[] books) {

    }

    @Override
    public void removeAllBooks(Collection<Book> books) {

    }

    @Override
    public void updateBook(Book book) {
//        "UPDATE BOOKS SET bookName = ?, publication = ?, dateOfPublication = ?, description = ?, price = ? WHERE BOOKID = ?";
        try {
            String[] dateParse = book.getDateOfPublication(true).split("-");
            String sqlType = dateParse[2] + "-" + dateParse[1] + "-" + dateParse[0];
            PreparedStatement updateBook = con.prepareStatement(UPDATEBOOK);
            updateBook.setString(1, book.getBookName());
            updateBook.setString(2, book.getPublication());
            updateBook.setDate(3, java.sql.Date.valueOf(sqlType));
//            updateBook.setString(3,book.getDateOfPublication(true));
            updateBook.setString(4, book.getDescription());
            updateBook.setDouble(5, book.getPrice());
            updateBook.setInt(6, book.getBookId());
            int rows = updateBook.executeUpdate();
            if (rows > 0) {
                System.out.println("UPDATED");
                PreparedStatement insertAuthor = con.prepareStatement(INSERTAUTHOR);
                PreparedStatement authorBook = con.prepareStatement(INSERTBOOKAUTHOR);
                for (String author : book.getAuthorNames()) {
                    insertAuthor.setString(1, author.toUpperCase());
                    insertAuthor.executeUpdate();
                    ResultSet authorSet = insertAuthor.getGeneratedKeys();
                    authorSet.next();
                    authorBook.setInt(1, book.getBookId());
                    authorBook.setInt(2, authorSet.getInt(1));
                    authorBook.executeUpdate();
                }
                authorBook.close();
                insertAuthor.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String convertDate(String date) {
        String[] dateParse = date.split("-");
        String newDate = dateParse[2] + "-" + dateParse[1] + "-" + dateParse[0];
        return newDate;
    }

    static void makeCallProcedure() {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3301/test?characterEncoding=UTF-8", "root", "");
                Statement statement = connection.createStatement();
            // Creating the stored procedure
            String createProcedureSql = "CREATE PROCEDURE update_book_prices() " +
                    "BEGIN " +
                    "   DECLARE price_threshold DECIMAL(10, 2); " +
                    "   SET price_threshold = 500.00; " +
                    "   UPDATE books SET price = price * 1.1 WHERE price > price_threshold; " +
                    "   SELECT CONCAT('Price updated for ', ROW_COUNT(), ' books.') AS Message; " +
                    "END";

            statement.executeUpdate(createProcedureSql);
            System.out.println("Stored procedure created successfully.");

            // Calling the stored procedure
            String callProcedureSql = "CALL update_book_prices()";
            statement.execute(callProcedureSql);
            System.out.println("Stored procedure called successfully.");
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        makeCallProcedure();
    }
}

