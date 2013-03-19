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
        System.out.println(port);
        System.out.println(ip);
    }
    
    public void run(){
        try{
            DatagramSocket aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while(true){
                System.out.println("HOLA");
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
