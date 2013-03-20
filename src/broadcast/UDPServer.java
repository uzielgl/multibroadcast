/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcast;

/**
 *
 * @author uzielgl
 */
import java.net.*;
import java.io.*;
import com.google.gson.Gson;
import java.util.Map;
import java.util.ArrayList;

public class UDPServer extends Thread{
    public String ip;
    public String port;
    public MainWindow frame;
    public Gson gson = new Gson();
    public BroadAlgorithm broad;
    
    public UDPServer( String ip, String port, MainWindow f ){
        this.ip = ip;
        this.port = port;
        frame = f;
    }
    
    public void run(){
        System.out.println("Levantando el servidor UDP en " + ip + " y puerto " + port);
        try{
            DatagramSocket aSocket = new DatagramSocket( Integer.parseInt( port ) );
            while(true){
                byte[] buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);     
                DatagramPacket reply = new DatagramPacket(request.getData(), 
                request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
                System.out.print("Recibe de UDP ");
                System.out.println( new String( request.getData(), "UTF-8" ).trim() );
                frame.broad.receiveMessage( gson.fromJson( new String( request.getData(), "UTF-8" ).trim(), ArrayList.class) );
                }
            }
            catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
    }
}
