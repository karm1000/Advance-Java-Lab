package assignmentrmi.books;

import assignment1.model.Book;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class GetBooksClient{
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("192.168.137.1",1099);
            GetBooksService fileService = (GetBooksService) registry.lookup("GetBooksService");

            List<Book> books = fileService.getBooks();
//            System.out.println(books);
            if(books!=null){
                System.out.println(books);
            }else{
                System.out.println("No Books Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
    }
}