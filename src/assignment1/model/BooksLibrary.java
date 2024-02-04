package assignment1.model;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BooksLibrary implements BasicLibrary,Serializable {
    DataStore store;
    TreeSet<Book> data;
    public BooksLibrary(DataStore store) throws IOException {
        this.store = store;
        this.data = store.getData();
    }

//    public BooksLibrary(TreeSet<Book> library) throws IOException {
//        super(library);
//    }

    @Override
    public void addBook(Book book) {
        data.add(book);
        store.writeIntoFile();
    }

    @Override
    public void addAllBooks(Book[] books) {
        data.addAll(Arrays.stream(books).toList());
        store.writeIntoFile();

    }

    public void addAllBooks(Collection<Book> books){
        data.addAll(books);
        store.writeIntoFile();
    }


    @Override
    public void removeBook(Book book) {
        data.remove(book);
        store.writeIntoFile();
    }

    @Override
    public void removeAllBooks() {
        data.clear();
        store.writeIntoFile();
    }

    @Override
    public void removeAllBooks(Book[] books) {
        data.removeAll(Arrays.stream(books).toList());
        store.writeIntoFile();
    }

    @Override
    public void removeAllBooks(Collection<Book> books) {
        data.removeAll(books);
        store.writeIntoFile();
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

    public void update(){
        store.writeIntoFile();
    }

    @Override
    public List<Book> filterBy(HashMap<String, String> queries){
        List<Book> filteredBooks = getAllBooks();
        for(Map.Entry<String,String> entry:queries.entrySet()){
            String key = entry.getKey();
            String val = entry.getValue();
            System.out.println(key+val);
            if(Objects.equals(key, "-")){
                filteredBooks = filteredBooks.stream().filter(book -> {
                    String bn1 = book.getBookName().toLowerCase();
                    String a = book.getAuthorNamesConcatenate().toLowerCase();
                    String v1 = val.toLowerCase();
                    return bn1.contains(v1) || v1.contains(bn1) || a.contains(v1);
                }).collect(Collectors.toList());
            }else {

                String op = key.substring(key.length() - 3).trim();
                key = key.substring(0, key.length() - 3);
                System.out.println(key+"-"+op+"-");
                String finalKey = key;
                filteredBooks = filteredBooks.stream().filter(book -> {

                    System.out.println("- - -"+book);
//                    System.out.println(finalKey+" and "+op+"=");

                    if(op.equals("=")){
                        System.out.println("--------=");
                        if (finalKey.equals(Book.PRICE)) {
                            return (int) book.getPrice() == (int) Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            return Objects.equals(book.getDateOfPublication(true), val);
                        }
                        return book.get(finalKey).toLowerCase().contains(val.toLowerCase()) || val.toLowerCase().contains(book.get(finalKey).toLowerCase());

                    }

                    else if(op.equals("!=")){
                        System.out.println("--------!=");
                        if (finalKey.equals(Book.PRICE)) {
                            return (int) book.getPrice() != (int) Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            return !Objects.equals(book.getDateOfPublication(true), val);
                        }
                        return !(book.get(finalKey).toLowerCase().contains(val.toLowerCase()) || val.toLowerCase().contains(book.get(finalKey).toLowerCase()));

                    }

                    else if(op.equals(">")){
                        System.out.println("-------->");
                        System.out.println("--------------"+book.getPrice() + " , "+val);
                        if (finalKey.equals(Book.PRICE)) {
                            return book.getPrice() > Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            return book.getDateOfPublication().isAfter(LocalDate.parse(val, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(ZoneId.systemDefault()));
                        }
                        return book.get(finalKey).compareTo(val) > 0;

                    }
                    else if(op.equals(">=")){
                        System.out.println("-------->=");
                        if (finalKey.equals(Book.PRICE)) {
                            return book.getPrice() >= Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            ZonedDateTime date = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(ZoneId.systemDefault());
                            return book.getDateOfPublication().equals(date) || book.getDateOfPublication().isAfter(date);
                        }
                        return book.get(finalKey).compareTo(val) >= 0;

                    }
                    else if(op.equals("<")){
                        System.out.println("--------<");
                        if (finalKey.equals(Book.PRICE)) {
                            return book.getPrice() < Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            ZonedDateTime date = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(ZoneId.systemDefault());
                            return book.getDateOfPublication().isBefore(date);
                        }
                        return book.get(finalKey).compareTo(val) < 0;

                    }
                    else if(op.equals("<=")){
                        System.out.println("--------<=");
                        if (finalKey.equals(Book.PRICE)) {
                            return book.getPrice() <= Float.parseFloat(val);
                        } else if (finalKey.equals(Book.DATEOFPUBLICATION)) {
                            ZonedDateTime date = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(ZoneId.systemDefault());
                            return book.getDateOfPublication().equals(date) || book.getDateOfPublication().isBefore(date);
                        }

                        return book.get(finalKey).compareTo(val) <= 0;

                    }

                    return true;

                }).collect(Collectors.toList());

            }
        }

        return filteredBooks;
    }

    @Override
    public String toString() {
        return "BooksLibrary{" +
                "library=" + data +
                '}';
    }
}
