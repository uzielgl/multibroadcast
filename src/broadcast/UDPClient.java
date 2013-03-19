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
public class UDPClient{
   

    
    public void sendMessage(String ip, String port, String msg){
        try {
		DatagramSocket aSocket = new DatagramSocket();    
		byte [] m = msg.getBytes();
		InetAddress aHost = InetAddress.getByName(ip);
		int serverPort = Integer.parseInt( port );		                                                 
		DatagramPacket request = new DatagramPacket(m,  msg.length(), aHost, serverPort);
		aSocket.send(request);			                        
		byte[] buffer = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
		//aSocket.receive(reply);
		aSocket.close();
	  }catch (SocketException e){
              System.out.println("Socket: " + e.getMessage());
	  }catch (IOException e){
              System.out.println("IO: " + e.getMessage());
          }
    }
}
