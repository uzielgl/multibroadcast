/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcast;

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
import java.util.ArrayList;

/**
 *
 * @author uzielgl
 */
public class MainWindow extends javax.swing.JFrame {
    public Map<String,HashMap> config;
    public Gson gson = new Gson();
    public Map<String,HashMap> client;
    public Map<String,HashMap> servers;
    
    public UDPServer udpServer;
    
    BroadAlgorithm broad;
    
    ArrayList proccessBtns = new ArrayList();
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        //Para crear mapas:
        //Map<String,HashMap> tmp = new HashMap<String,HashMap>();
        initComponents();
        
        /*
        loadConfig( new File( "C:\\Users\\uzielgl\\Documents\\p1.txt" ) );
        createSendButtons();
        */
    }
    
    
    public void startServer(){
        //Inicializamos el servidor
        String key_client = client.keySet().iterator().next();
        Map<String,String> map_client = client.get(key_client);
        udpServer = new UDPServer( map_client.get( "ip" ), map_client.get("port"), this );
        udpServer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        pnlSendButtons = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistory = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane1.setViewportView(txtMessage);

        pnlSendButtons.setLayout(new javax.swing.BoxLayout(pnlSendButtons, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        txtHistory.setColumns(20);
        txtHistory.setRows(5);
        jScrollPane2.setViewportView(txtHistory);

        jMenu2.setText("Archivo");

        jMenuItem1.setText("Abrir configuración...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSendButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlSendButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto: (.txt)", "txt"));
        int seleccion = fileChooser.showOpenDialog(this);
        if ( seleccion == JFileChooser.APPROVE_OPTION ){
            loadConfig( fileChooser.getSelectedFile() );
            createSendButtons();
            startServer();
            broad = new BroadAlgorithm(this);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void loadConfig( File config_file){
        try{
            Scanner config_scanner = new Scanner( config_file );
            String config_text = "";
            while( config_scanner.hasNext() ){
                config_text += config_scanner.next();
            }
            config = gson.fromJson( config_text, Map.class );
            client = config.get("client");
            servers = config.get("servers");
        }catch(IOException e){
        }
    }    
    
    public void createSendButtons(){ 
        javax.swing.JLabel lbl = new javax.swing.JLabel("Enviar a:");
        lbl.setAlignmentX( Component.CENTER_ALIGNMENT );
        pnlSendButtons.add( lbl );
        
        Iterator it = servers.entrySet().iterator();
        while( it.hasNext() ){
            Map.Entry e = (Map.Entry) it.next();
            addAButton( (String) e.getKey(), pnlSendButtons );
        }
        pnlSendButtons.revalidate();
        pnlSendButtons.repaint();
    }
    
    public void addAButton(String text, Container container) {
        javax.swing.JButton button = new javax.swing.JButton(text);
        proccessBtns.add( button );
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String proccess = ( (javax.swing.JButton) e.getSource() ).getText();
                broad.sendMessage(proccess, txtMessage.getText());
            }
        });
        container.add(button);
    }
    
    /** 
     * Agrega los mensajes al txtHistory
     */
    public void addHistory( String msg ){
        txtHistory.setText( txtHistory.getText() + "\n" + msg);
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlSendButtons;
    private javax.swing.JTextArea txtHistory;
    private javax.swing.JTextArea txtMessage;
    // End of variables declaration//GEN-END:variables
}
