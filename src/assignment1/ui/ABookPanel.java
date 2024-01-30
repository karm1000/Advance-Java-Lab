package assignment1.ui;

import assignment1.controller.ABookPanelActions;
import assignment1.model.Book;
import assignment1.util.FormatAttributeName;

import javax.swing.*;
import java.awt.*;

public class ABookPanel extends JPanel {
    JPanel container;
    JLabel bookName;
    JLabel authorsName;
    JLabel description;
    JLabel publication;
    JLabel publishingDate;
    JLabel price;
    public JPanel optionPanel;
    public JButton removeBook;
    public Book book;
    ABookPanelActions actions;
    DashboardFrame main;

    public ABookPanel(Book aBook,DashboardFrame main) {
        super(new BorderLayout());
        this.book = aBook;
        this.main = main;
        container = new JPanel(new GridBagLayout());
        actions = new ABookPanelActions(this,main);
        optionPanel = new JPanel(new GridBagLayout());
        this.addMouseListener(actions);
//        container.setBorder(BorderFactory.createLineBorder(Color.black,3));
        GridBagConstraints constraints = new GridBagConstraints();
        init();

        container.add(bookName,getConstraints(constraints,0,0));
        container.add(authorsName,getConstraints(constraints,0,1));
        container.add(description,getConstraints(constraints,0,2));
        container.add(publication,getConstraints(constraints,0,3));
        container.add(publishingDate,getConstraints(constraints,0,4));
        container.add(price,getConstraints(constraints,0,5));

        container.setOpaque(false);
        this.setMaximumSize(new Dimension(1000,125));
        this.setPreferredSize(new Dimension(1000, 125)); // Adjust the preferred size as needed
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));

        removeBook = new JButton("Delete");
        removeBook.setPreferredSize(new Dimension(100,30));
        removeBook.addActionListener(actions);

        optionPanel.setBackground(Color.white);
        optionPanel.setPreferredSize(new Dimension(100,125));
        optionPanel.setOpaque(false);
        optionPanel.add(removeBook);

        this.setBackground(Color.white);
        this.add(optionPanel,BorderLayout.EAST);
        this.add(container, BorderLayout.CENTER);
    }

    private void init() {
        bookName = new JLabel(FormatAttributeName.formatAttribute(Book.BOOKNAME)+ " : " +this.book.getBookName());
        bookName.setFont(new Font("Arial", Font.BOLD, 20));
        description = new JLabel(FormatAttributeName.formatAttribute(Book.DESCRIPTION)+ " : " +this.book.getDescription());
//        bookName.setBorder(BorderFactory.createLineBorder(Color.pink));
        authorsName = new JLabel(FormatAttributeName.formatAttribute(Book.AUTHORNAMES)+ " : " +String.join(", ", this.book.getAuthorNames()));
        publication = new JLabel(FormatAttributeName.formatAttribute(Book.PUBLICATION)+ " : " +this.book.getPublication());
        publishingDate = new JLabel(FormatAttributeName.formatAttribute(Book.DATEOFPUBLICATION) + " : " + this.book.getDateOfPublication(true));
        price = new JLabel(FormatAttributeName.formatAttribute(Book.PRICE)+ " : " +String.valueOf(this.book.getPrice()));
    }

    public void update(){
        bookName.setText(this.book.get(Book.BOOKNAME));
        authorsName.setText(this.book.get(Book.AUTHORNAMES));
        description.setText(this.book.get(Book.DESCRIPTION));
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

    @Override
    public String toString() {
        return "ABookPanel{" +
                "book=" + book +
                ", super="+super.toString()+
                '}';
    }
}
