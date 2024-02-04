package assignment1.ui;

import assignment1.model.Book;
import assignment1.model.BooksLibrary;
import assignment1.model.FileIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AllBooksPanel extends JPanel {
    NavBar navBar;
    JScrollPane scrollPane;
    JPanel booksContainer;
    JScrollBar scrollBar;
    BooksLibrary library;

    DashboardFrame main;
    LinkedHashMap<Book,ABookPanel> map = new LinkedHashMap<>();
    JLabel noBooksLabel = new JLabel("No Books!");
    int length = 0;

    public AllBooksPanel(DashboardFrame main){
        super();
        this.main = main;
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
            Iterator<Book> i = main.library.getIterator();
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
        for(Book book:main.library.getAllBooks()){
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

    public void filterBooks(HashMap<String,String> queries){
        List<Book> filteredBooks = main.library.filterBy(queries);

        booksContainer.removeAll();
        map = new LinkedHashMap<>();
        for (Book b : filteredBooks) {
            System.out.println(b);
            map.put(b, new ABookPanel(b, main));
        }
        renderBooks();
    }

}
