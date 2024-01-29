package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class SearchBar extends JPanel {
    JTextField textField;
    JButton btn;
    SearchBar(){
        super(new FlowLayout(FlowLayout.CENTER,0,0));
        Font font = new Font("Arial", Font.PLAIN, 17);
        textField = new JTextField(100);
        textField.setPreferredSize(new Dimension(1000,35));
        textField.setFont(new Font("Consolas",Font.PLAIN,16));
        btn = new JButton("search");
        btn.setFont(font);
//        this.setBackground(Color.red);
        this.add(textField);
        this.add(btn);
    }
}
