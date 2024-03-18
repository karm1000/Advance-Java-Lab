package assignmentrmi.os;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

class Meta{
    String osName;
    String osVersion;
    String osArch;
    String hardDiskSize;
    String hardDiskFreeSize;

    public Meta(String osName, String osVersion, String osArch, String hardDiskSize, String hardDiskFreeSize, String totalMemory, String memoryUsage) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArch = osArch;
        this.hardDiskSize = hardDiskSize;
        this.hardDiskFreeSize = hardDiskFreeSize;
        this.totalMemory = totalMemory;
        this.memoryUsage = memoryUsage;
    }

    String totalMemory;

    @Override
    public String toString() {
        return "{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArch='" + osArch + '\'' +
                ", hardDiskSize='" + hardDiskSize + '\'' +
                ", hardDiskFreeSize='" + hardDiskFreeSize + '\'' +
                ", totalMemory='" + totalMemory + '\'' +
                ", memoryUsage='" + memoryUsage + '\'' +
                '}';
    }

    String memoryUsage;
}

public class GetOSInfoImplementation extends UnicastRemoteObject implements GetOSInfoService {
    public GetOSInfoImplementation() throws RemoteException{
        super();
    }
    @Override
    public String getOsInfo() throws RemoteException {
        String meta = "";
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");
        File file = new File("/");
        String hardDiskSize = humanReadableByteCountBin(file.getTotalSpace());
        String hardDiskFreeSize = humanReadableByteCountBin(file.getFreeSpace());
//        System.out.println(file.getTotalSpace()+" - " + file.getFreeSpace());
        Runtime runtime = Runtime.getRuntime();
        String totalMemory = humanReadableByteCountBin(runtime.totalMemory());
        String memoryUse = humanReadableByteCountBin(runtime.totalMemory()-runtime.freeMemory());

//        System.out.println(runtime.maxMemory());

        return new Meta(osName,osVersion,osArch,hardDiskSize,hardDiskFreeSize,totalMemory,memoryUse)+"";
    }

    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }
}
