package assignment1.ui;

import assignment1.controller.ABookPanelActions;
import assignment1.model.Book;

import javax.swing.*;
import java.awt.*;

public class ABookPanel extends JPanel {
    JPanel container;
    JLabel bookName;
    JLabel authorsName;
    JLabel publication;
    JLabel publishingDate;
    JLabel price;
    JButton removeBook;
    public Book book;
    ABookPanelActions actions;
    DashboardFrame main;

    public ABookPanel(Book aBook,DashboardFrame main) {
        super(new BorderLayout());
        this.book = aBook;
        this.main = main;
        container = new JPanel(new GridBagLayout());
        actions = new ABookPanelActions(this,main);
        this.addMouseListener(actions);
//        container.setBorder(BorderFactory.createLineBorder(Color.black,3));
        GridBagConstraints constraints = new GridBagConstraints();
        init();

        container.add(bookName,getConstraints(constraints,0,0));
        container.add(authorsName,getConstraints(constraints,0,1));
        container.add(publication,getConstraints(constraints,0,2));
        container.add(publishingDate,getConstraints(constraints,0,3));
        container.add(price,getConstraints(constraints,0,4));

        container.setOpaque(false);
        this.setMaximumSize(new Dimension(1000,125));
        this.setPreferredSize(new Dimension(1000, 125)); // Adjust the preferred size as needed
        this.setBorder(BorderFactory.createLineBorder(Color.black,3));

        removeBook = new JButton("Delete");
        removeBook.setPreferredSize(new Dimension(100,50));
        removeBook.addActionListener(e->{
            main.library.removeBook(book);
            main.allBooksPanel.update();
        });

        this.add(removeBook,BorderLayout.EAST);
        this.add(container, BorderLayout.CENTER);
    }

    private void init() {
        bookName = new JLabel(this.book.getBookName());
        bookName.setFont(new Font("Arial", Font.BOLD, 20));
//        bookName.setBorder(BorderFactory.createLineBorder(Color.pink));
        authorsName = new JLabel(String.join(", ", this.book.getAuthorNames()));
        publication = new JLabel(this.book.getPublication());
        publishingDate = new JLabel(String.valueOf(this.book.getDateOfPublication(true)));
        price = new JLabel(String.valueOf(this.book.getPrice()));
    }

    public void update(){
        bookName.setText(this.book.get(Book.BOOKNAME));
        authorsName.setText(this.book.get(Book.AUTHORNAMES));
        publication.setText(this.book.get(Book.PUBLICATION));
        publishingDate.setText(this.book.get(Book.DATEOFPUBLICATION));
        price.setText(this.book.get(Book.PRICE));
    }

    private GridBagConstraints getConstraints(GridBagConstraints constraints, int gridx, int gridy) {
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        return constraints;
    }
}
