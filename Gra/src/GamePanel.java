import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
	
	public static int width;
	public static int height;
	
	private Thread thread; 
	private boolean running;
	private BufferedImage img;
	private Graphics2D g;
	
	public GamePanel(int width, int height) {
		GamePanel.width = width;
		GamePanel.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		setBackground(Color.black);
		
	}
	
	public void addNotify() {
		super.addNotify();
		
		if (thread ==null) {
			thread = new Thread (this, "Game Thread");
			thread.start();
		}
	}
	
	public void init() {
		running = true;
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
	}
	
	public void run() {
		init();
		while(running) {
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		for (Planet pr : planets) {
//			pr.paint(g);
//		}

	}
}
