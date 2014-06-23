package test0;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class testsev1 {
	 private JFrame frame = new JFrame();
	 private JDesktopPane jdesktop = new JDesktopPane();
	 public static void main(String args[]){
	        String port = JOptionPane.showInputDialog("svp entrez le port :");
	        new testsev1().debut(Integer.parseInt(port));
	    }
	 
	 public void debut(int port){

	        try {
	            @SuppressWarnings("resource")
				ServerSocket sc = new ServerSocket(port);
	            
	            GUI();
	           
	            while(true){
	            	
	                Socket client = sc.accept();
	                JOptionPane.showMessageDialog(null, "un client s'est connecté au server");
	                new Clientconnec(client,jdesktop);
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	 //interface graphique
	 public void GUI(){
         frame.add(jdesktop,BorderLayout.CENTER);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
         frame.setVisible(true);
 }
	 
 
}
