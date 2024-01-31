//package assignment1.model;
//
//import assignment1.model.BasicLibrary;
//import assignment1.model.Book;
//import assignment1.model.BooksLibrary;
//import assignment1.ui.DashboardFrame;
//
//import java.io.*;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.TreeSet;
//
//public class DataBooksLibrary implements BasicLibrary {
//
//    BooksLibrary data;
//    File dataFile;
//
//    public DataBooksLibrary() throws IOException {
//        try {
//            dataFile = new File("src/assignment1/data/Library.dat");
//            if(dataFile.exists()){
//                try (FileInputStream fileInputStream = new FileInputStream(dataFile);
//                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
//                    TreeSet<Book> set =  new TreeSet<>((TreeSet<Book>) objectInputStream.readObject());
//                    this.data = new BooksLibrary(set);
//                    System.out.println(data);
//
//                }catch (EOFException e){
//                    this.data = new BooksLibrary();
//                    System.out.println(e);
//                    System.out.println("-----");
//                }
//            }else{
//                dataFile.createNewFile();
//                data = new BooksLibrary();
//            }
//        }catch (Exception e){
//            System.out.println(e);
//            System.out.println("*****");
//        }
//    }
//
//    @Override
//    public void addBook(Book book) {
//        data.addBook(book);
//        writeIntoFile();
//    }
//
//    @Override
//    public void removeBook(Book book) {
//        data.removeBook(book);
//        writeIntoFile();
//    }
//
//    @Override
//    public void removeAll() {
//        data.removeAll();
//        writeIntoFile();
//    }
//
//    @Override
//    public void addAll(Book[] books) {
//        data.addAll(books);
//        writeIntoFile();
//    }
//
//    private void writeIntoFile(){
//        try(FileOutputStream fos = new FileOutputStream(dataFile);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//        ) {
//            oos.writeObject(data.getLibrary());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void update(){
//        writeIntoFile();
//    }
//
//    public List<Book> getAllBooks(){
//        return data.getLibrary().stream().toList();
//    }
//
//    public Iterator<Book> getIterator(){
//        return data.getLibrary().iterator();
//    }
//
//    public boolean contains(Book book){
//        return data.getLibrary().contains(book);
//    }
//}
