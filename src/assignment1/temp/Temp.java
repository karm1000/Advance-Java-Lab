package assignment1.temp;

import java.io.File;
import java.io.IOException;

public class Temp {
    public static void main(String[] args) throws IOException {
        System.out.println(new File("/").getAbsolutePath());
        File file = new File("src/assignment1/data/Library.dat");
////        filePath
//        System.out.println(file);
        System.out.println(file.exists());
        file.createNewFile();
        System.out.println(file.exists());

    }
}
