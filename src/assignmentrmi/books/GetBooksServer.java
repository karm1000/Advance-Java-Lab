package assignmentrmi.books;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GetBooksServer{
    public static void main(String[] args) {
        try {
            GetBooksService fileService = new GetBooksImplementation();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GetBooksService", fileService);
            System.out.println("Books Server Ready ...");

        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
    }
}