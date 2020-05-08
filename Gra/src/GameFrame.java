import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GameFrame {
	
	//GamePanel panel;
	private JFrame frame;
	private BufferedImage img;
	private Graphics g;
	private BufferStrategy bs;
	private Canvas canvas;
	
	public GameFrame(GameEngine ge){
		
		img = new BufferedImage(ge.getWidth(), ge.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension s = new Dimension ((int)(ge.getWidth()*ge.getScale()),(int)(ge.getHeight() * ge.getScale()));
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);
		
		frame = new JFrame();
		//frame.setSize(new Dimension(ge.getWidth(), ge.getHeight()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		//panel = new GamePanel(900, 700);
		


		/*ExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.execute(panel);
		exec.shutdown();*/
		/*this.add(panel, BorderLayout.CENTER);
		this.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);*/
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		
	}
	public void update() {
		g.drawImage(img, 0, 0, canvas.getWidth(),canvas.getHeight(), null);
		bs.show();
	}
}
