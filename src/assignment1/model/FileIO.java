package assignment1.model;


import com.sun.source.tree.Tree;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

public abstract class FileIO implements BasicLibrary {
    File dataFile;
    TreeSet<Book> data;

    public FileIO() throws IOException {
        try {
            dataFile = new File("src/assignment1/data/Library.dat");
            if(dataFile.exists()){
                try (FileInputStream fileInputStream = new FileInputStream(dataFile);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    TreeSet<Book> set =  new TreeSet<Book>((TreeSet<Book>) objectInputStream.readObject());
                    this.data = new TreeSet<>(set);
                    System.out.println(data);

                }catch (EOFException e){
                    this.data = new TreeSet<>();
                    System.out.println(e);
                    System.out.println("-----");
                }
            }else{
                dataFile.createNewFile();
                this.data = new TreeSet<>();
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("*****");
        }
    }

     public void addBook(Book book) {
        add(book);
        writeIntoFile();
    }

    public void addAllBooks(Book[] books) {
        addAll(books);
        writeIntoFile();
    }

    public void addAllBooks(Collection<Book> books) {
        addAll(books);
        writeIntoFile();
    }

    public void removeBook(Book book) {
        remove(book);
        writeIntoFile();
    }

    public void removeAllBooks() {
        removeAll();
        writeIntoFile();
    }

    public void removeAllBooks(Book[] books) {
        removeAll(books);
        writeIntoFile();
    }

    public void removeAllBooks(Collection<Book> books) {
        removeAll(books);
        writeIntoFile();
    }

    public void update(){
        writeIntoFile();
    }

    private void writeIntoFile(){
        try(FileOutputStream fos = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
