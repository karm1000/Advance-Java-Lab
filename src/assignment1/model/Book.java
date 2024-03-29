package assignment1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Book implements Serializable, Comparable<Book>,Cloneable {
    private static int nextId = 1;
    private int bookId;
    private String bookName = "";
    private List<String> authorNames = new ArrayList<>();
    private String description = "";
    private String publication = "";
    private ZonedDateTime dateOfPublication = ZonedDateTime.now();
    private float price = 0.0f;
    public static final String BOOKID = "bookId";
    public static final String BOOKNAME = "bookName";
    public static final String AUTHORNAMES = "authorNames";
    public static final String DESCRIPTION = "description";

    public static final String PUBLICATION = "publication";
    public static final String DATEOFPUBLICATION = "dateOfPublication";
    public static final String PRICE = "price";
    public static final String[] attributeList = {BOOKNAME,AUTHORNAMES,DESCRIPTION,PUBLICATION,DATEOFPUBLICATION,PRICE};
//    private HashMap<String,String> map = new HashMap<>();
    public Book(){
//        bookId = nextId++;
    }

    public Book(int bookId){
        this.bookId = bookId;
    }
    public Book(String bookName){
        this();
        setBookName(bookName);
    }

    public Book(String bookName, String authorName){
        this();
        setBookName(bookName);
        setAuthorNames(authorName);
    }

    public Book(String bookName,List<String> authorNames){
        this();
        setBookName(bookName);
        setAuthorNames(authorNames);
    }

    public Book(String bookName, List<String> authorNames,String publication,ZonedDateTime dateOfPublication,float price) throws Exception {
        this(bookName,authorNames);
        setPublication(publication);
        setDateOfPublication(dateOfPublication);
        setPrice(price);
    }
    public Book(String bookName,String[] authorNames,String publication,ZonedDateTime dateOfPublication,float price) throws Exception {
        this(bookName,Arrays.stream(authorNames).toList(),publication,dateOfPublication,price);
    }

    public Book(String bookName,String authorName,String publication,ZonedDateTime dateOfPublication,float price) throws Exception {
        this(bookName,authorName);
        setPublication(publication);
        setDateOfPublication(dateOfPublication);
        setPrice(price);
    }

    public void setBookId(int i){
        this.bookId = i;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
//        map.put(BOOKNAME,bookName);
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
//        map.put(AUTHORNAMES, getAuthorNamesConcatenate());
    }

    public void setAuthorNames(String[] authorNames){
        setAuthorNames(Arrays.stream(authorNames).toList());
    }

    public void setAuthorNames(String authorNames){
        String[] names = authorNames.split(",");
        for(int i=0;i<names.length;i++){
            names[i] = names[i].trim();
        }
        setAuthorNames(names);
    }

    public void setDescription(String description){
        this.description = description;
//        map.put(DESCRIPTION,description);
    }
    public void setPublication(String publication) {
        this.publication = publication;
//        map.put(PUBLICATION,publication);
    }

    public void setDateOfPublication(ZonedDateTime dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
//        map.put(DATEOFPUBLICATION,getDateOfPublication(true));
    }

    public void setDateOfPublication(String date) throws DateTimeParseException {
        LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dateOfPublication = newDate.atStartOfDay(ZoneId.systemDefault());
//        map.put(DATEOFPUBLICATION,getDateOfPublication(true));
    }

    public void setPrice(float price) throws NumberFormatException{
        if(price<0) throw new NumberFormatException("No Negative Value allowed as price");
        this.price = price;
//        map.put(PRICE,String.valueOf(price));
    }

    public void setPrice(String val) throws NumberFormatException {
        setPrice(Float.parseFloat(val));
    }

    public void set(String key,String value) throws DateTimeParseException,NumberFormatException {
        switch (key){
            case BOOKNAME -> {
                setBookName(value);
            }
            case PUBLICATION -> {
                setPublication(value);
            }
            case DATEOFPUBLICATION -> {
                setDateOfPublication(value);
            }
            case PRICE -> {
                setPrice(value);
            }
            case AUTHORNAMES -> {
                setAuthorNames(value);
            }
            case DESCRIPTION -> {
                setDescription(value);
            }
        }
    }
    public int getBookId(){
        return bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public String[] getAuthorNames() {
        return authorNames.toArray(new String[0]);
    }
    public String getDescription(){
        return description;
    }

    public String getAuthorNamesConcatenate(){
        return String.join(", ", authorNames);
    }

    public String getPublication() {
        return publication;
    }

    public ZonedDateTime getDateOfPublication() {
        return dateOfPublication;
    }
    public String getDateOfPublication(boolean isString){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(dateOfPublication);
    }

    public String get(String key){
        if(key.equals(BOOKNAME)){
            return bookName;
        }else if(key.equals(AUTHORNAMES)){
            return getAuthorNamesConcatenate();
        }else if(key.equals(DESCRIPTION)){
            return getDescription();
        } else if (key.equals(DATEOFPUBLICATION)) {
            return getDateOfPublication(true);
        }else if(key.equals(PRICE)){
            return ""+getPrice();
        }else if(key.equals(PUBLICATION)){
            return getPublication();
        }
        return "";
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorNames=" + authorNames +
                ", publication='" + publication + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return this.bookName.compareTo(o.getBookName());
    }
}
