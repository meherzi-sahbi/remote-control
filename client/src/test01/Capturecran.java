package test01;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import javax.imageio.*;  
import java.net.Socket;
import javax.swing.ImageIcon;
//import java.util.Iterator;  

public class Capturecran extends Thread{

	 Socket socket = null; 
	    Robot robot = null; 
	    Rectangle rectangle = null; 
	     //++ 
	    
	    public Capturecran(Socket socket, Robot robot,Rectangle rect) {
	        this.socket = socket;
	        this.robot = robot;
	        rectangle = rect;
	        start();
	    }
	    public void run(){
	        ObjectOutputStream oos = null; 


	        try{
	         
	            oos = new ObjectOutputStream(socket.getOutputStream());
 
	            oos.writeObject(rectangle);
	        }catch(IOException ex){
	            ex.printStackTrace();
	        }	

	       while(true){
	            
	            BufferedImage image = robot.createScreenCapture(rectangle);
	        //    BufferedImage originalImage = robot.createScreenCapture(rectangle); //++
	           
	          
	            
	            
	            ImageIcon imageIcon = new ImageIcon(image);
	            

	            //envoi image capture
	            try {
	                
	                oos.writeObject(imageIcon);
	                oos.reset(); 
	               
	            } catch (IOException ex) {
	               ex.printStackTrace();
	            }

	           
	        }

	    }
	
}
