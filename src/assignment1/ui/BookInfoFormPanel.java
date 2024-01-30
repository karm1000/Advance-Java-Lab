package assignment1.ui;

import assignment1.model.Book;
import assignment1.util.FormatAttributeName;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class BookInfoFormPanel extends JPanel{
    LinkedHashMap<String,FieldPanel> fields;
    Book bookInfo;
    DashboardFrame main;
    public ABookPanel currentTarget = null;
    public BookInfoFormPanel(DashboardFrame main){
        super();
        this.main = main;
        this.bookInfo = new Book();
//        this.setLayout(new BoxLay
//        out(this,BoxLayout.Y_AXIS));
//        this.setLayout(new GridLayout(Book.attributeList.length, 1,10,10));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        this.setPreferredSize(new Dimension(main.getWidth(),this.getPreferredSize().height));
        fields = new LinkedHashMap<>();
        for(String key:Book.attributeList){
            fields.put(key,new FieldPanel(FormatAttributeName.formatAttribute(key),this));
        }

        Iterator<FieldPanel> iterator = fields.values().iterator();
//       gbc.weightx = 1;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.NORTHWEST;
        int j = 0;

        while(iterator.hasNext()){
            this.add(iterator.next(),getConstraints(gbc,0,j++));
        }
        this.setBackground(Color.white);
        this.setVisible(true);
    }

    public void setAll(Book book){
        this.bookInfo = book;
        main.bookInfoPanel.showEdit();
        for(String key:Book.attributeList){
            fields.get(key).setTextField(bookInfo.get(key));
        }
        disableAll();
    }

    public void updateBookInfo() {
        try{

            FieldPanel fpanel;
            for(String key:Book.attributeList){
                fpanel = fields.get(key);
                bookInfo.set(key,fpanel.textField.getText());
            }
        }catch (Exception e){
            System.out.println(e);
        }

        if(currentTarget!=null){
            currentTarget.book = bookInfo;
            System.out.println(currentTarget.book);
            System.out.println(bookInfo);
        }
    }


    void disableAll(){
        Iterator<FieldPanel> i = fields.values().iterator();
        while(i.hasNext()){
            i.next().disableTextField();
        }
    }

    void enableAll(){
        Iterator<FieldPanel> i = fields.values().iterator();
        while(i.hasNext()){
            i.next().enableTextField();
        }
    }
    public void clearAll(){
        Iterator<FieldPanel> i = fields.values().iterator();
        while(i.hasNext()){
            i.next().clearTextField();
        }
    }

    public Book addBook(){
        bookInfo = new Book();
        try{
            FieldPanel fpanel;
            for(String key:Book.attributeList){
                fpanel = fields.get(key);
                bookInfo.set(key,fpanel.textField.getText());
            }
        }catch (Exception e){
            System.out.println(e);
        }
        main.library.addBook(bookInfo);
//        System.out.println(bookInfo);
        return bookInfo;
    }

    public void setCurrentTarget(ABookPanel panel){
        this.currentTarget = panel;
    }

    private GridBagConstraints getConstraints(GridBagConstraints constraints, int gridx, int gridy) {
//        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
//        constraints.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        return constraints;
    }

}
