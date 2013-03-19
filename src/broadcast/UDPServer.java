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
public class UDPServer extends Thread{
    public String ip;
    public String port;
    
    public UDPServer( String ip, String port ){
        this.ip = ip;
        this.port = port;
    }
    
    public void run(){
        System.out.println("Levantando el servidor UDP");
        try{
            DatagramSocket aSocket = new DatagramSocket( Integer.parseInt( port ) );
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);     
                DatagramPacket reply = new DatagramPacket(request.getData(), 
                request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
                }
            }
            catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
    }
}
