package assignmentrmi.os;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GetOSInfoClient {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            GetOSInfoService fileService = (GetOSInfoService) registry.lookup("GetOSInfo");
            String osInfo = fileService.getOsInfo();
            System.out.println(osInfo);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
