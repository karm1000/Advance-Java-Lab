package assignment1.ui;

import assignment1.controller.SearchActions;
import assignment1.util.SearchVerifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SearchBar extends JPanel {
    public JTextField textField;
    JButton btn;
    DashboardFrame main;
    SearchActions actions;
    SearchVerifier verifier;
    private static final HashMap<String,String> operators = new HashMap<>();
    SearchBar(DashboardFrame main){
        super(new FlowLayout(FlowLayout.CENTER,0,0));
        initOperatorHashMap();
        this.main = main;
        actions = new SearchActions(this,main);
        verifier = new SearchVerifier();
        Font font = new Font("Arial", Font.PLAIN, 17);
        textField = new JTextField(100);
        textField.setPreferredSize(new Dimension(1000,35));
        textField.setFont(new Font("Consolas",Font.PLAIN,16));
        textField.setInputVerifier(verifier);
        textField.getDocument().addDocumentListener(actions);

        btn = new JButton("search");
        btn.setFont(font);
        btn.addActionListener(actions);
//        this.setBackground(Color.red);
        this.add(textField);
        this.add(btn);
    }

    public HashMap<String,String> getSearch(){
        if(textField.getText().trim().length()==0) return new HashMap<>();
        HashMap<String,String> map = new HashMap<>();
        String[] queries = splitString(textField.getText().trim());
        for (String q:
             queries) {
            System.out.println(q);
        }
        if(queries.length==1){
            System.out.println("Only one");
            queries[0] = queries[0].trim();
            for(String op:operators.keySet()){
                if(queries[0].contains(op)){
                    System.out.println(op);
                    int index = queries[0].indexOf(op);
                    String key = queries[0].substring(0,index).trim()+ String.format("%3s",operators.get(op));
                    String val = queries[0].substring(index+op.length(),queries[0].length()-1).trim();
                    map.put(key,val);
                    System.out.println("->>"+key+"-"+val);
                    return map;
                }
            }
            if(queries[0].charAt(0)=='\"' && queries[0].charAt(queries[0].length()-1)=='\"'){
                map.put("-",queries[0].substring(1,queries[0].length()-1).trim());
            }else{
                map.put("-",queries[0].trim());
            }
            return map;
        }

        loop: for(String str:queries){
            for(String op:operators.keySet()){
                if(str.contains(op)){
                    int index = str.indexOf(op);
                    String key = str.substring(0,index).trim()+String.format("%3s",operators.get(op));
                    String val = str.substring(index+op.length(),str.length()-1).trim();
                    map.put(key,val);
                    continue loop;
                }
            }
        }

        return map;
    }

    private static String[] splitString(String s){
        if(s=="") return new String[]{""};
        s = s.trim();
        ArrayList<String> list = new ArrayList<>();
        if(s.charAt(s.length()-1)!=','){
            s = s + ',';
        }
        int l = 0;
        int count = 0;
        for(int i=0;i<s.length();i++){
//            System.out.println(i+" "+s.charAt(i));

            if(s.charAt(i)==','){
                if(count%2==0){
                    list.add(s.substring(l,i).trim());
                    l = i + 1;
                }
            }


            if(s.charAt(i)=='\"') count++;

        }

        return (list.size()==0?new String[]{s}:list.toArray(new String[0]));
    }

    private static void initOperatorHashMap(){
        operators.put(">\"",">");
        operators.put(">=\"",">=");
        operators.put(":\"","=");
        operators.put("=\"","=");
        operators.put("<\"","<");
        operators.put("<=\"","<=");
        operators.put("!=\"","!=");
    }

//    public static void main(String[] args) {
//        initOperatorHashMap();
////        String s = "bookName:\"H C V\"";
//        String s = "bookName:\"H C V\", authorNames:\"H ,C V\", price<=\"32\" ";
//
//        HashMap<String,String> map = getSearch(s);
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
//        }
//    }
}
