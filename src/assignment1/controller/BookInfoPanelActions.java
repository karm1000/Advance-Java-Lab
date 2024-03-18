package assignment1.controller;

import assignment1.model.Book;
import assignment1.ui.BookInfoPanel;

import java.awt.event.*;

public class BookInfoPanelActions extends MouseAdapter implements ActionListener {
    BookInfoPanel bookInfoPanel;
    public BookInfoPanelActions(BookInfoPanel bookInfoPanel){
        this.bookInfoPanel = bookInfoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bookInfoPanel.addBtn){
            Book[] aBook = bookInfoPanel.formPanel.addBook();
            if(aBook.length!=0){
                bookInfoPanel.main.allBooksPanel.addBook(aBook[0]);
                bookInfoPanel.showEdit();
            }
        } else if (e.getSource()==bookInfoPanel.newBtn) {
            bookInfoPanel.formPanel.clearAll();
            bookInfoPanel.showAdd();
        } else if (e.getSource()==bookInfoPanel.editBtn) {
            bookInfoPanel.showUpdate();
        } else if (e.getSource()==bookInfoPanel.updateBtn) {
            bookInfoPanel.formPanel.updateBookInfo();
            bookInfoPanel.formPanel.currentTarget.render();
//            bookInfoPanel.main.library.update();
            bookInfoPanel.showEdit();
        }
    }
}
