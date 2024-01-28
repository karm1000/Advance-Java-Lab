package assignment1.util;

import assignment1.model.BooksLibrary;

import java.io.*;
import java.util.Collection;

public class ObjectDataOperations {
    FileOutputStream fos;
    FileInputStream fis;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    File dataFile;
    BooksLibrary data;
    public ObjectDataOperations() throws IOException {
        try {
            dataFile = new File("src/assignment1/data/Library.dat");
            if(dataFile.exists()){
                try (FileInputStream fileInputStream = new FileInputStream(dataFile);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                    this.data = (BooksLibrary) objectInputStream.readObject();

                }catch (IOException e){
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

}
