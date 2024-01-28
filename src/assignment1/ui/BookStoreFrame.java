package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class BookStoreFrame extends JFrame {
    public BookStoreFrame(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(400, 300));
        this.setResizable(true);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

