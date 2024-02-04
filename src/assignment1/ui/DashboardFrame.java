package assignment1.ui;

import assignment1.model.BooksLibrary;
import assignment1.model.FileIO;
//import assignment1.model.DataBooksLibrary;

import java.awt.*;
import java.io.IOException;

public class DashboardFrame extends BookStoreFrame {
    public AllBooksPanel allBooksPanel;
    public BookInfoPanel bookInfoPanel;
    public BooksLibrary library;
    DashboardFrame() throws IOException {
        super();
        init();
        this.setVisible(true);
    }

    private void init(){
        try{
            this.library = new BooksLibrary(new FileIO());
        }catch (IOException e){
            System.out.println(e);
        }
        this.allBooksPanel = new AllBooksPanel(this);
        this.bookInfoPanel = new BookInfoPanel(this);
        this.setLayout(new BorderLayout(2,2));
        this.add(allBooksPanel,BorderLayout.CENTER);
        this.add(bookInfoPanel,BorderLayout.EAST);
    }

    public static void main(String[] args) throws IOException {
        new DashboardFrame();
    }
}
