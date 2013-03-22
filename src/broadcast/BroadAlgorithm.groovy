/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package broadcast
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;
import java.util.Set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.*;
import java.io.*;

/**
 *
 * @author uzielgl
 */
class BroadAlgorithm {
    public UDPClient udpClient = new UDPClient();
    public Map config;
    public Map client;
    public Map servers;
    public MainWindow frame;
    public Gson gson = new Gson();
    public int vector_pos;
    public String name_proccess;
    public String separator = "\n--------------------------------------------------------\n";
    
    public VT = [0,0,0,0];
    public hm = [];
    public ci = [];
    public message_send = [];
    
    public cola_mensajes = []; 
    
    public BroadAlgorithm( MainWindow frame ){
        this.frame = frame;
        config = frame.config;
        client = config.get("client");
        servers = config.get("servers");
        
        name_proccess = client.keySet().iterator().next();
        vector_pos = Integer.parseInt( name_proccess[1] ) - 1 ;
        
        addHistory("Inicializando Vector " + name_proccess + " = " + VT + " y CI = [ ] " + separator);
    }
    
    /**
     * proccess es el proceso al que se envia p1|p2|p3|p4 
     **/
    public sendMessage(proccess, msg){
        //Se debe de mostrar el mensaje enviado cada vez que den click a Enviar a ? 
        if( allActives() ){ //Aquí es en la difusión del mensaje
            VT[ vector_pos ]++;
            hm = ci;
            message_send = [ vector_pos, VT[ vector_pos], msg, hm];
            ci = [];
            addHistory(" Difusión del mensaje. \n    Mensaje: " + message_send  + "  \n   VT: " + VT);
            addHistory( message_send );
        }
        
        //String pro = client.keySet().iterator().next(); //El proceso actual
        //def message = [ msg : msg, proccess : pro, vector_pos : vector_pos, i : vector_pos,  ]; //Agregar todas las demás variables
        print "Envia: ";
        print message_send;
        print "\n";
        print "Envia en json:";
        print gson.toJson(message_send);
        print "\n";
        
        udpClient.sendMessage( servers[proccess]["ip"], servers[proccess]["port"], gson.toJson(message_send) );
        
        
        enableTxtMessage(false);
        disableBtnProccess( proccess  );
        
        if( allDisableBtnsProccess() ){
            enableAllBtnsProccess();
            enableTxtMessage(true);
        }
    }
    
    public receiveMessage( message ){
        print "recibe";
        println message;
        int k = message[0];
        int tk = message[1];
        def hm = message[3];
        if( ! ( ( tk == ( VT[ k ] + 1 ) ) && isCausal(VT, hm) ) ){
            print "wait... Encolar el mensaje y con cada recepción intentar entregarlo (llamar a esta misma función)";
            addColaMensaje( message );
            addHistory("***Esperando mensaje de p" + ( k + 1) + "*** "  + ". Cola de mensajes: " + cola_mensajes + separator);
            return false;
        }else{
            VT[k]++;
            ci = deleteKS( k, ci); 
            ci.add( [k, tk] );
            ci = deleteHmCi( hm, ci);
            addHistory( "Recepción de mensaje de p" + ( k + 1) + ". \n    Mensaje: " + message + "\n    VT: " + VT + "\n    CI: " + ci   );
            addHistory( message );
            
            receiveOtherMessages();
            
            return true;
        }
    }
    
    public receiveOtherMessages(){
        def se_entrego_mensaje = true;
        while( se_entrego_mensaje ){
            se_entrego_mensaje = false;
            
            for (int i=0; i<cola_mensajes.size(); i++) {  
                def m = cola_mensajes[i];
                cola_mensajes.remove( i );
                if( receiveMessage( m ) ){
                    break;
                }else{
                    addColaMensaje( m );
                }
            }  
            
        }
    }
    
    /** Ingresa mensajes no repetidos a la cola*/
    public addColaMensaje( message ){
        Iterator it = cola_mensajes.iterator();
        def add = true;
        while( it.hasNext() ){
            def msg = it.next();
            if( msg[0] == message[0] && msg[1] == message[1] ){
                add = false;
                break;
            }
        }
        if( add == true) 
            cola_mensajes.add( message );
    }
    
    /** Elimina todas las tuplas de Hm que están en Ci
    * Nota: Regresa tuplas repetidas
    **/
    def deleteHmCi(Hm, Ci){
        Iterator it_ci = Ci.iterator();
        def new_ci = [];
        while( it_ci.hasNext() ) {
            def t_ci = it_ci.next();
            Iterator it_hm = Hm.iterator();
            def add = true;
            while( it_hm.hasNext() ) {
                def t_hm = it_hm.next();
                if(  t_ci[0] == t_hm[0] && t_ci[1] == t_hm[1]  ){
                    add = false;
                    break;
                }
            }
            if (add == true){
               new_ci.add( t_ci );
            }
        }
        return new_ci;
    }

    
    /** Elimina todas las tuplas que k=s del ci
     **/
    public deleteKS( int k, ArrayList ci ){
        Iterator it = ci.iterator();
        def new_ci = [];
        while( it.hasNext() ){
            def t = it.next();
            if( !( t[0] == k ) )
                new_ci.add( t );
        }
        return new_ci;
    }
    
    
    
    /** Define si pasa la condición causal
     * @param vt [1,0,1,2]
     * @param hm [ [2, 3], [2,4] ]
     * @return boolean  - Define si pasa o no la condición
     **/
    public isCausal( ArrayList vt, ArrayList hm ){
        println "vt en causal : " + vt;
        println "hm en causal : " + hm;
        Iterator it =  hm.iterator();
        while( it.hasNext() ){
            def t = it.next();
            int l = t[0];
            int tl = t[1];
            print tl + " <= " + vt[l];
            println tl <= vt[l]
            
            if( ! ( tl <= vt[l] ) )
                return false;
        }
        return true;
    }
    
    public enableTxtMessage( boolean val){
        frame.txtMessage.setEnabled(val);
    }
    
    /* Habilita todos los botones
     **/
    public enableAllBtnsProccess(){
        Iterator it = frame.proccessBtns.iterator();
        while( it.hasNext() ){
            def button = it.next();
            button.setEnabled(true);
        }
    }
    
    /** Checa si todos los botones están deshabilitados
     **/
    public allDisableBtnsProccess(){
        Iterator it = frame.proccessBtns.iterator();
        while( it.hasNext() ){
            def button = it.next();
            if( button.isEnabled() )
                return false;
        }
        return true;
    }
    
    /** Deshabilita un botón de los procesos*/
    public void disableBtnProccess(String proccess){
        Iterator it = frame.proccessBtns.iterator();
        while( it.hasNext() ){
            def button = it.next();
            if( button.getText() == proccess )
                button.setEnabled(false);
        }
    }
    
    /** Determina si todos los botones de los procesos están activos*/
    public allActives(){
        Iterator it = frame.proccessBtns.iterator();
        while( it.hasNext() ){
            def button = it.next();
            if( !button.isEnabled() )
                return false;
        }
        return true;
    }
    

    
    /** 
     *@param message Un objeto con multiples propiedades como proccess (proceso), msg (mensaje), history (array de historia), vector etc.
     **/
    public addHistory( ArrayList message ){
        frame.addHistory( ( message[0] + 1 ) + ": " + message[2] + separator);
    }
    
    public addHistory( String message ){
        frame.addHistory( message );
    }
    
    
} 

