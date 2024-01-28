package assignment1.ui;

import assignment1.model.Book;

import javax.swing.*;
import java.awt.*;

public class BookInfoPanel extends JPanel {
    private static final String UPDATE_BTN = "update";
    private static final String ADD_BTN = "add";
    private static final String NEW_BTN = "new";

    private static final String EDIT_BTN = "edit";
    BookInfoFormPanel formPanel;
    CardLayout cardLayout;
    JPanel btnPanel;
    JButton addBtn;
    JButton updateBtn;
    JButton editBtn;
    JButton newBtn;
    BookInfoPanel(DashboardFrame main){
        super();
        Dimension btnSize = new Dimension(200,50);

        addBtn = new JButton("Add Book");
        addBtn.addActionListener(e -> {
//            System.out.println("Someting");
            Book aBook = formPanel.addBook();
            main.allBooksPanel.addBook(aBook);
            showEdit();
        });

        updateBtn = new JButton("Update Book");
        updateBtn.addActionListener(e -> {
            formPanel.updateBookInfo();
            formPanel.currentTarget.update();
            main.library.update();
            showEdit();
        });

        editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> showUpdate());

        newBtn = new JButton("New Book");
        newBtn.setPreferredSize(btnSize);
        newBtn.addActionListener(e -> {
            formPanel.clearAll();
            showAdd();
        });


        cardLayout = new CardLayout();
        btnPanel = new JPanel(cardLayout);
        btnPanel.setPreferredSize(btnSize);
        btnPanel.add(updateBtn,UPDATE_BTN);
        btnPanel.add(editBtn,EDIT_BTN);
        btnPanel.add(addBtn,ADD_BTN);

        formPanel = new BookInfoFormPanel(main);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(400, main.getHeight()));

        this.add(formPanel,BorderLayout.CENTER);
        this.add(btnPanel,BorderLayout.SOUTH);
        this.add(newBtn,BorderLayout.NORTH);

        this.setVisible(true);
    }

    public void showUpdate() {
        formPanel.enableAll();
        cardLayout.show(btnPanel,UPDATE_BTN);
    }

    public void showEdit() {
        formPanel.disableAll();
        cardLayout.show(btnPanel,EDIT_BTN);
    }

    public void showAdd(){
        formPanel.enableAll();
        cardLayout.show(btnPanel,ADD_BTN);
    }

}
