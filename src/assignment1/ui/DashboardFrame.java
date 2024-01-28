package assignment1.ui;

import assignment1.model.BooksLibrary;
import assignment1.util.DataBooksLibrary;

import java.awt.*;
import java.io.IOException;

public class DashboardFrame extends BookStoreFrame {
    AllBooksPanel allBooksPanel;
    BookInfoPanel bookInfoPanel;
    DataBooksLibrary library = new DataBooksLibrary();
    DashboardFrame() throws IOException {
        super();
        init();
        this.setVisible(true);
    }

    private void init(){
        this.allBooksPanel = new AllBooksPanel(this);
        this.bookInfoPanel = new BookInfoPanel(this);
        this.setLayout(new BorderLayout(10,10));
        this.add(allBooksPanel,BorderLayout.CENTER);
        this.add(bookInfoPanel,BorderLayout.EAST);
    }

    public static void main(String[] args) throws IOException {
        new DashboardFrame();
    }
}