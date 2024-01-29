package assignment1.controller;

import assignment1.ui.ABookPanel;
import assignment1.ui.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ABookPanelActions extends MouseAdapter {
    ABookPanel ref;
    DashboardFrame main;

    public ABookPanelActions(ABookPanel ref, DashboardFrame main){
        this.ref = ref;
        this.main = main;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(11);
        ref.setBorder(BorderFactory.createLineBorder(Color.blue,3));
        ref.setBackground(new Color(151, 198, 255));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ref.setBorder(BorderFactory.createLineBorder(Color.black,3));
        ref.setBackground(Color.white);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            main.bookInfoPanel.formPanel.setCurrentTarget(ref);
            main.bookInfoPanel.formPanel.setAll(ref.book);
            System.out.println(ref.book);
        }
    }
}
