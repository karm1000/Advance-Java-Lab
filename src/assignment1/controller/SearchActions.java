package assignment1.controller;

import assignment1.ui.DashboardFrame;
import assignment1.ui.SearchBar;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SearchActions implements ActionListener, DocumentListener {
    DashboardFrame main;
    SearchBar searchBar;
    public SearchActions(SearchBar parent, DashboardFrame main){
        this.main = main;
        this.searchBar = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        HashMap<String,String> map = searchBar.getSearch();
        if(map.size()!=0){
            main.allBooksPanel.filterBooks(map);
        }
        else{
            main.allBooksPanel.renderAll();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        warn();
    }

    void warn(){
        if(!searchBar.textField.isValid()){
            searchBar.textField.setForeground(Color.red);
        }else{
            searchBar.textField.setForeground(Color.black);
        }
        searchBar.textField.repaint();
    }


}
