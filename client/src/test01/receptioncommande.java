package test01;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class receptioncommande extends Thread{

	 Socket socket = null;
	    Robot robot = null;
	    public receptioncommande(Socket s, Robot r) {
	       socket = s;
	        robot = r;
	        start();
	    }
	
	
	    @SuppressWarnings("resource")
		public void run(){
	        Scanner scan = null;
	        try {
	         
	      
	            scan = new Scanner(socket.getInputStream());

	            while(true){
	               
	                int command = scan.nextInt();//reception de commande
	             
	                switch(command){
	                    case -1:
	                        robot.mousePress(scan.nextInt());
	                    break;
	                    case -2:
	                        robot.mouseRelease(scan.nextInt());
	                    break;
	                    case -3:
	                        robot.keyPress(scan.nextInt());
	                    break;
	                    case -4:
	                        robot.keyRelease(scan.nextInt());
	                    break;
	                    case -5:
	                        robot.mouseMove(scan.nextInt(), scan.nextInt());
	                    break;
	                }
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	
	
	
	
	
	
	
	
}
