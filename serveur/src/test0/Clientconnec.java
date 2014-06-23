package test0;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class Clientconnec extends Thread {
	private JDesktopPane jdesktop = null;
    private Socket cSocket = null;
    private JInternalFrame interFrame = new JInternalFrame("ecran client",
                                                            true, true, true);
    private JPanel cPanel = new JPanel();

    public Clientconnec(Socket cSocket, JDesktopPane desktoppanel) {
        this.cSocket = cSocket;
        this.jdesktop = desktoppanel;
        start();
    }
    public void GUI(){
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        jdesktop.add(interFrame);
        try {
           
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }
    public void run(){

       
        Rectangle dimclientecran = null;
     
        ObjectInputStream ois = null;//captur ecran & dim
        
        GUI();

        try{
            // dimension ecran
            ois = new ObjectInputStream(cSocket.getInputStream());
            dimclientecran =(Rectangle) ois.readObject();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
      
        new receptioncapture(ois,cPanel);
       
        new envoicomm(cSocket,cPanel,dimclientecran);
    }

}
