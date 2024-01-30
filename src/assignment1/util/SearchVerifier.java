package assignment1.util;

import javax.swing.*;

public class SearchVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String s = ((JTextField) input).getText();
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='"') count++;
        }
        return count%2==0;
    }
}
