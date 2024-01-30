package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class NavBar extends JPanel {
    SearchBar searchBar;
    NavBar(JComponent component,DashboardFrame main){
        super(new GridBagLayout());
        this.searchBar = new SearchBar(main);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(component.getWidth(),50));
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(searchBar,BorderLayout.CENTER);
//        panel.setSize(new Dimension(frame.getWidth(),panel.getPreferredSize().height));
        this.add(panel);
    }

}
