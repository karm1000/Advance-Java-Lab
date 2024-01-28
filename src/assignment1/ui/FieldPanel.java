package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    JLabel label;
    JTextField textField;

    public FieldPanel(String labelText, JComponent component) {
        super();
        this.setLayout(new BorderLayout(5,5));
//        System.out.println("_------------------>"+component.getWidth());
        label = new JLabel(labelText);
        textField = new JTextField(400);
//        textField.setSize(new Dimension(component.getWidth(), 35));
        textField.setFont(new Font("Consolas",Font.PLAIN,15));
//        this.setPreferredSize(new Dimension(component.getPreferredSize().width,50));
//        this.setSize(component.getWidth(),50);
        this.setMinimumSize(new Dimension(400,55));
        this.add(label,BorderLayout.NORTH);
        this.add(textField,BorderLayout.CENTER);
        this.setBackground(Color.white);
    }

    void setLabel(String text){
        label.setText(text);
    }

    void setTextField(String text){
        textField.setText(text);
    }

    void disableTextField(){
        textField.setEditable(false);
    }

    void enableTextField(){
        textField.setEditable(true);
    }

    void clearTextField(){
        textField.setText("");
    }
}
