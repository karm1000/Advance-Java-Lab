package assignment1.controller;

import assignment1.ui.ABookPanel;
import assignment1.ui.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ABookPanelActions extends MouseAdapter implements ActionListener {
    ABookPanel ref;
    DashboardFrame main;

    public ABookPanelActions(ABookPanel ref, DashboardFrame main){
        this.ref = ref;
        this.main = main;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println(11);
        ref.setBorder(BorderFactory.createLineBorder(Color.blue,2));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ref.setBorder(BorderFactory.createLineBorder(Color.black,2));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==ref){
            if(e.getClickCount()==2){
                main.bookInfoPanel.formPanel.setCurrentTarget(ref);
                main.bookInfoPanel.formPanel.setAll(ref.book);
                System.out.println(ref.book);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ref.removeBook){
            int choice = JOptionPane.showConfirmDialog(null,"Do You Want to delete "+ref.book.getBookName(),"Deletion",JOptionPane.WARNING_MESSAGE);
            if(choice == JOptionPane.YES_OPTION){
                main.library.removeBook(ref.book);
                System.out.println(main.library.getAllBooks());
                main.allBooksPanel.removeBook(ref.book);
            }
        }
    }
}
