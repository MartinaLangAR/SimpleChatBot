import java.io.*;  
import java.net.*; 
import java.lang.*;

public class client {

public static void main(String[] args) {  



    try{      
        Socket socket=new Socket("localhost",2004);  

        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());  
        DataInputStream din=new DataInputStream(socket.getInputStream());


        dout.writeUTF("Hello");
        dout.flush();

        System.out.println("send first mess");
        String str = din.readUTF();

        System.out.println("Message from Server :"+str);


        dout.close();  
        din.close();
        socket.close();
        }

    catch(Exception e){
        e.printStackTrace();}   


}  

}