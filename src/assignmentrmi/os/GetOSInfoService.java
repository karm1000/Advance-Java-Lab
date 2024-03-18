package assignmentrmi.os;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GetOSInfoService extends Remote {
    String getOsInfo() throws RemoteException;

}
