package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class SearchBar extends JPanel {
    JTextField textField;
    JButton btn;
    SearchBar(){
        super(new FlowLayout(FlowLayout.LEFT,2,2));
        textField = new JTextField(70);
        btn = new JButton("search");
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setBackground(Color.red);
        this.add(textField);
        this.add(btn);
    }
}
