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
    
    public BroadAlgorithm( MainWindow frame ){
        this.frame = frame;
        config = frame.config;
        client = config.get("client");
        servers = config.get("servers");
    }
    
    /**
     * proccess es el proceso al que se envia p1|p2|p3|p4 
     **/
    public sendMessage(proccess, msg){
        String pro = client.keySet().iterator().next(); //El proceso actual
        def message = [ msg : msg, proccess : pro ]; //Agregar todas las demás variables
        
        udpClient.sendMessage( servers[proccess]["ip"], servers[proccess]["port"], gson.toJson(message) );
        
        //Se debe de mostrar el mensaje enviado cada vez que den click a Enviar a ? 
        addHistory( message );
        
        disableBtnProccess( proccess  );
        
        if( allDisableBtnsProccess() ){
            enableAllBtnsProccess();
        }
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
            print button.getText();
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
    
    public receiveMessage( message ){
        print "aplicar condiciones y actualizar\n";
        addHistory( message );
    }
    
    /** 
     *@param message Un objeto con multiples propiedades como proccess (proceso), msg (mensaje), history (array de historia), vector etc.
     **/
    public addHistory( message ){
        frame.addHistory( message.proccess + ": " + message.msg );
    }
    
} 

