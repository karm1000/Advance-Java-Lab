package assignment1.model;

import java.util.TreeSet;

public interface DataStore {
    void init();
    TreeSet<Book> getData();
    void writeIntoFile();

}
