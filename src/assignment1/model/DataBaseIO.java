//package assignment1.model;
//
//import com.mysql.jdbc.Driver;
//import com.sun.source.tree.Tree;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Arrays;
//import java.util.TreeSet;
//
//public abstract class DataBaseIO implements BasicLibrary {
//    TreeSet<Book> data;
//    private Connection con;
//    DataBaseIO(TreeSet<Book> data){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306:test/","root", "");
//        }
//        catch(Exception e)
//        {
//            System.out.println("Error : "+e.getMessage());
//        }
//    }
//
//    @Override
//    public void addBook(Book book) {
//        data.add(book);
//        writeIntoFile();
//    }
//
//    @Override
//    public void addAll(Book[] books) {
//        data.addAll(Arrays.stream(books).toList());
//        writeIntoFile();
//    }
//
//    @Override
//    public void removeAll() {
//        data.clear();
//        writeIntoFile();
//    }
//
//    @Override
//    public void removeBook(Book book) {
//        data.remove(book);
//        writeIntoFile();
//    }
//
//    public void update(){
//        writeIntoFile();
//    }
//
//    private void writeIntoFile(){
//
//    }
//}
