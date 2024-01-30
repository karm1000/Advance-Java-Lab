package assignment1.util;

public class FormatAttributeName {
    public static String formatAttribute(String key) {
        String labelText = ""+Character.toUpperCase(key.charAt(0));
        for(int i=1;i<key.length();i++){
            if(Character.isUpperCase(key.charAt(i))){
                labelText += " ";
            }
            labelText += key.charAt(i);
        }
        return labelText;
    }
}
