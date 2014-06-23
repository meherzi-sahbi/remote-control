package test0;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

public class envoicomm implements KeyListener, MouseMotionListener,
		MouseListener {

	private Socket cSocket = null;
	private JPanel cPanel = null;
	private PrintWriter pwriter = null;
	private Rectangle dimensioncecran = null;

	envoicomm(Socket s, JPanel p, Rectangle r) {
		cSocket = s;
		cPanel = p;
		dimensioncecran = r;

		cPanel.addKeyListener(this);
		cPanel.addMouseListener(this);
		cPanel.addMouseMotionListener(this);
		try {

			pwriter = new PrintWriter(cSocket.getOutputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void mouseMoved(MouseEvent e) {
		double x = dimensioncecran.getWidth() / cPanel.getWidth();
		System.out.println("x: " + x);
		double y = dimensioncecran.getHeight() / cPanel.getHeight();
		System.out.println("y: " + y);
		System.out.println("Mouse Moved");
		pwriter.println(-5);
		pwriter.println((int) (e.getX() * x));
		pwriter.println((int) (e.getY() * y));
		pwriter.flush();
	}

	public void mousePressed(MouseEvent e) {

		pwriter.println(-1);
		int button = e.getButton();
		int xButton = 16;
		if (button == 3) {
			xButton = 4;
		}
		pwriter.println(xButton);
		pwriter.flush();
	}

	public void mouseReleased(MouseEvent e) {

		pwriter.println(-2);
		int button = e.getButton();
		int xButton = 16;
		if (button == 3) {
			xButton = 4;
		}
		pwriter.println(xButton);
		pwriter.flush();
	}

	public void keyPressed(KeyEvent e) {

		pwriter.println(-3);
		pwriter.println(e.getKeyCode());
		pwriter.flush();
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("Mouse Released");
		pwriter.println(-4);
		pwriter.println(e.getKeyCode());
		pwriter.flush();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}