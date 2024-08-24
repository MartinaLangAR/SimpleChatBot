import java.io.*;  
import java.net.*; 
import java.net.InetSocketAddress;
import java.lang.*;

public class Client {
    static Socket socket = new Socket();
    static DataOutputStream dout;  //= new DataOutputStream(socket.getOutputStream()); 
    static DataInputStream din; //= new DataInputStream(socket.getInputStream());  

    public static boolean est_con() throws UnknownHostException, IOException{
        socket.connect(new InetSocketAddress("localhost", 2004));
        dout = new DataOutputStream(socket.getOutputStream());
        din = new DataInputStream(socket.getInputStream());
        return true;
    }

    public static void send_msg(String msg) {  

        try{     
            //DataOutputStream dout = new DataOutputStream(socket.getOutputStream()); 
            dout.writeUTF(msg);
            dout.flush();
            System.out.println("sent message");
            }

        catch(Exception e){
            e.printStackTrace();}   


    }  

    public static String reicv_msg() {  

        try{    
            //DataInputStream din = new DataInputStream(socket.getInputStream());  
            String str = din.readUTF();
            System.out.println("Message from Server :"+str); 
            return str;
            }

        catch(Exception e){
            e.printStackTrace();   
        return "An Exception ocuured during receiving from server";
        }
    }  

    public static boolean close_con() {
        try {
            socket.close();
            dout.close();
            din.close();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}