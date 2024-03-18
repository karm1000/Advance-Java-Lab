package NETWORK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReadFileTCPClient {
    public static void main(String[] args) {
        try{
//            Socket client = new Socket("localhost",6000);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(args[0]+"  "+args[1]);
//            System.out.print("Enter File Name : ");
//            String filename = br.readLine();
//        }catch (UnknownHostException e){
//            System.out.println(e);
//        }catch (IOException e){
//            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
