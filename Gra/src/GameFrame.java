import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;

public class GameFrame {
	
	//GamePanel panel;
	
	private JFrame frame;
	private BufferedImage img;
	private Graphics g;
	private BufferStrategy bs;
	private Canvas canvas;
	
	public GameFrame(){
		
		img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension s = new Dimension (800,800);
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
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		
	}
	
	public void update(List<Planet> planets, Player player) {
//		System.out.println(player.calcPosX(player.getX(), player.getY(), canvas.getWidth()));
//		System.out.println(player.calcPosY(player.getX(), player.getY(), canvas.getHeight()));
		
		g.drawImage(img, 0, 0, canvas.getWidth(),canvas.getHeight(), null);
		for(Planet pr: planets) {
			g.setColor(pr.getColor());
			g.fillOval(
					player.calcPosX(pr.getX(), pr.getY(), frame.getWidth()) - pr.getSelfR(),
					player.calcPosY(pr.getX(), pr.getY(), frame.getHeight()) - pr.getSelfR(),
					2*pr.getSelfR(),
					2*pr.getSelfR()
					);
		}
		g.setColor(Color.cyan);
		g.fillRect(
				player.calcPosX((int)player.getX(), (int)player.getY(), frame.getWidth()),
				player.calcPosY((int)player.getX(), (int)player.getY(), frame.getWidth()),
				10, 10);
		bs.show();
		
	}
	public BufferedImage getImg() {
		return img;
	}
	public Canvas getCanvas() {
		return canvas;
	}
}
