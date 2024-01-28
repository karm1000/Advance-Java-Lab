package assignment1.ui;

import assignment1.model.Book;
import assignment1.model.BooksLibrary;
import assignment1.util.DataBooksLibrary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class AllBooksPanel extends JPanel {
    NavBar navBar;
    JScrollPane scrollPane;
    JPanel booksContainer;
    JScrollBar scrollBar;
    DataBooksLibrary library;
    DashboardFrame main;
    HashSet<Book> set = new HashSet<Book>();
    JLabel noBooksLabel = new JLabel("No Books!");
    int lastLength = 0;

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
            renderBooks();
            lastLength = library.getAllBooks().size();
            System.out.println(10);
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
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setSize(10,10);

        navBar = new NavBar(this);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(navBar, BorderLayout.NORTH);
    }

    public void renderBooks(){
        if(library.getAllBooks().isEmpty()){
            booksContainer.add(noBooksLabel);
        }else{
            library.getAllBooks().forEach(book -> {
                ABookPanel aBookPanel = new ABookPanel(book,main);
                set.add(book);
                booksContainer.add(aBookPanel);
                booksContainer.add(Box.createVerticalStrut(10));
            });
        }
    }

    public void update(){
        if(lastLength==0){
            booksContainer.remove(noBooksLabel);
            renderBooks();
            lastLength = library.getAllBooks().size();
            return;
        }
        if(lastLength!=library.getAllBooks().size()){
            Iterator<Book> i = library.getIterator();
            while(i.hasNext()){
                Book book = i.next();
                if(!set.contains(book)){
                    set.add(book);
                    booksContainer.add(new ABookPanel(book,main));
                    booksContainer.add(Box.createVerticalStrut(10));
                }
            }
            lastLength = library.getAllBooks().size();
        }
    }
    public void addBook(Book book){
        if(lastLength==0){
            booksContainer.remove(noBooksLabel);
        }
        lastLength = library.getAllBooks().size();
        booksContainer.add(new ABookPanel(book,main));
        booksContainer.add(Box.createVerticalStrut(10));
    }

    public void remove(Book book){

    }

}
