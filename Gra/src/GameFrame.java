import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	GamePanel panel;
	private BufferedImage img;
	private Graphics g;
	private BufferStrategy bs;
	private Canvas canvas;
	
	public GameFrame(){
		
		
		
		setSize(new Dimension(900,700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new GamePanel(900, 700);
		canvas = new Canvas();
		Dimension s = new Dimension (900, 700);
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);

		img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);

		/*ExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.execute(panel);
		exec.shutdown();*/
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		
	}
	public void update() {
		g.drawImage(img, 0, 0, canvas.getWidth(),canvas.getHeight(), null);
		bs.show();
	}
}
