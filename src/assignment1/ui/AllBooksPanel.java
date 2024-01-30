package assignment1.ui;

import assignment1.model.Book;
import assignment1.model.DataBooksLibrary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AllBooksPanel extends JPanel {
    NavBar navBar;
    JScrollPane scrollPane;
    JPanel booksContainer;
    JScrollBar scrollBar;
    DataBooksLibrary library;

    DashboardFrame main;
    LinkedHashMap<Book,ABookPanel> map = new LinkedHashMap<>();
    JLabel noBooksLabel = new JLabel("No Books!");
    int length = 0;

    public AllBooksPanel(DashboardFrame main){
        super();
        this.main = main;
        this.library = main.library;
        this.setLayout(new BorderLayout());
        init();
        this.setVisible(true);
    }

    private void init(){
        booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS));
        booksContainer.setBorder(new EmptyBorder(10,10,10,10));

        try{
            renderAll();
//            System.out.println(10);
    //        for(int i=0;i<10;i++){
    //            booksContainer.add(new ABookPanel(new Book("A Book"+i,"An Author","A Publication" ,ZonedDateTime.now(),10),main));
    //            booksContainer.add(Box.createVerticalStrut(10));
    //        }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e+"------------------");
        }

        // Add more ABookPanel instances as needed

        scrollPane = new JScrollPane(booksContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane.getVerticalScrollBar().setSize(10,10);

        navBar = new NavBar(this,main);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(navBar, BorderLayout.NORTH);
    }

    public void renderBooks(){
        if(map.isEmpty()){
            booksContainer.add(noBooksLabel);
        }else{

            for(ABookPanel panel:map.values()){
                booksContainer.add(panel);
                booksContainer.add(Box.createVerticalStrut(10));
            }
        }
        booksContainer.repaint();
        booksContainer.revalidate();

    }

    public void update(){
        if(length==0){
            booksContainer.remove(noBooksLabel);
            renderBooks();
            length = library.getAllBooks().size();
            return;
        }
        if(length!=library.getAllBooks().size()){
            Iterator<Book> i = library.getIterator();
            while(i.hasNext()){
                Book book = i.next();
                if(!map.containsKey(book)){
                    ABookPanel aBookPanel = new ABookPanel(book,main);
                    map.put(book,aBookPanel);
                    booksContainer.add(aBookPanel);
                    booksContainer.add(Box.createVerticalStrut(10));
                }
            }
            length = library.getAllBooks().size();
        }
    }

    public void renderAll(){
        map.clear();
        booksContainer.removeAll();
        for(Book book:library.getAllBooks()){
            map.put(book, new ABookPanel(book,main));
        }
        renderBooks();
        length = map.size();
    }

    public void addBook(Book book){

        ABookPanel aBookPanel = new ABookPanel(book,main);
        map.put(book,aBookPanel);
        renderAll();
    }

    public void removeBook(Book book){
//        System.out.println(map.get(book));
        length--;
        booksContainer.remove(map.get(book));
        map.remove(book);
        if(length==0){
            booksContainer.add(noBooksLabel);
        }
        booksContainer.repaint();
    }

    public void filterBy(HashMap<String,String> queries){
        List<Book> filteredBooks = library.getAllBooks();
        for(Map.Entry<String,String> entry:queries.entrySet()){
            String key = entry.getKey();
            String val = entry.getValue();
            System.out.println(key+val);
            if(key=="-"){
                filteredBooks = filteredBooks.stream().filter(book -> {
                    return book.getBookName().contains(val) || val.contains(book.getBookName());
                }).collect(Collectors.toList());
            }else {

                String op = key.substring(key.length() - 3).trim();
                key = key.substring(0, key.length() - 3);
                System.out.println("-"+op+"-");
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

        booksContainer.removeAll();
        map = new LinkedHashMap<>();
        Iterator<Book> i = filteredBooks.iterator();
        while(i.hasNext()){
            Book b = i.next();
            System.out.println(b);
            map.put(b,new ABookPanel(b,main));
        }
        renderBooks();
    }

}
