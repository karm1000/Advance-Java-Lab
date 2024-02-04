package assignment1.model;


import java.io.*;
import java.util.*;

public class FileIO implements DataStore {
//    File dataFile = "src/assignment1/data/Library.dat";
    File dataFile  = new File("src/assignment1/data/Library.dat");;
    TreeSet<Book> data = new TreeSet<>();
    public FileIO(){init();}

    public FileIO(String filename){
        dataFile = new File("src/assignment1/data/"+filename);
        init();
    }
    public void init(){
        try {
            if(dataFile.exists()){
                try (FileInputStream fileInputStream = new FileInputStream(dataFile);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    TreeSet<Book> set =  new TreeSet<>((TreeSet<Book>) objectInputStream.readObject());
                    this.data.addAll(set);
                    System.out.println(data);

                }catch (EOFException e){
                    System.out.println(e);
                    System.out.println("-----");
                }
            }else{
                dataFile.createNewFile();
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("*****");
        }
    }

    public TreeSet<Book> getData(){
        return data;
    }
    public void update(){
        writeIntoFile();
    }

    public void writeIntoFile(){
        try(FileOutputStream fos = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
