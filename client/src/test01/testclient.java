package test01;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class testclient {
	Socket socket = null;

    public static void main(String[] args){
        String ip = JOptionPane.showInputDialog("SVP entrez ip serveur ");
        String port = JOptionPane.showInputDialog("SVP entrez port serveur");
        new testclient().debut(ip,Integer.parseInt(port));
    }
    
    public void debut(String ip, int port ){

        Robot robot = null; 
        Rectangle rectangle = null; 

        try {
           
            socket = new Socket(ip, port);
          

            //preparer class robot
            GraphicsEnvironment genv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=genv.getDefaultScreenDevice();

            //dimension
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

           
          
            robot = new Robot(gDev);

            GUI();
            new Capturecran(socket,robot,rectangle);
            
            new receptioncommande(socket,robot);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (AWTException ex) {
                ex.printStackTrace();
        }
    }

    private void GUI() {
        JFrame frame = new JFrame("client");
        JButton button= new JButton("terminer");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
    }
}
