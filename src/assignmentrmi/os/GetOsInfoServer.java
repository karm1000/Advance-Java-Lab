package assignmentrmi.os;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GetOsInfoServer {
    public static void main(String[] args) {
        try {

            GetOSInfoService getOsInfo = new GetOSInfoImplementation();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GetOSInfo", getOsInfo);
            System.out.println("Server Ready ...");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
