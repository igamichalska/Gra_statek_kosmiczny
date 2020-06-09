import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameFrame implements WindowListener{
	
	private JFrame frame;
	private BufferedImage img, img2;
	private Graphics2D g;
	private BufferStrategy bs;
	private Canvas canvas;
	String file;
	URL url;
	
	
	public GameFrame(){
		
		img = new BufferedImage(1400, 880, BufferedImage.TYPE_INT_RGB);
		img2 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		if (StartWindow.cmd == "Czerwony") {
				file =  "pic_red.png";
			}
		else if(StartWindow.cmd == "Niebieski") {
				file =  "pic_blue.png";
			}
		else if(StartWindow.cmd == "Szary") {
				file =  "pic_grey.png";
			}
		url = getClass().getResource(file);
		if (url != null) {
            try {
                img2 = ImageIO.read(url);
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
		}
		
		canvas = new Canvas();
		Dimension s = new Dimension (1400, 880);
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(this);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = (Graphics2D)bs.getDrawGraphics();
		
		
	}
	
	public void update(List<Planet> planets, Player player) {
		
		g.drawImage(img, 0, 0, canvas.getWidth(),canvas.getHeight(), null);
		
		
		g.translate(canvas.getWidth()/2, canvas.getHeight()/2);
		g.translate(-(int)player.getX(), -(int)player.getY());
		g.rotate(-Math.toRadians(player.getAng()+90), player.getX(), player.getY());
		
			for(Planet pr: planets) {
				g.setColor(Color.gray); g.drawOval(-pr.getR(), -pr.getR(), 2*pr.getR(), 2*pr.getR());
				g.setColor(pr.getColor());
				g.fillOval(
						pr.getX()-pr.getSelfR(),
						pr.getY()-pr.getSelfR(),
						2*pr.getSelfR(),2*pr.getSelfR());
				
			}
			g.setColor(Color.cyan);
			g.fillRect(
					(int)player.getX(),
					(int)player.getY(),
					10, 10);
			g.drawLine(
					(int)player.getX(),
					(int)player.getY(), 
					(int)player.getX() + (int)(40*Math.cos(Math.toRadians(player.getAng()))),
					(int)player.getY() + (int)(40*Math.sin(Math.toRadians(player.getAng()))));
			
			
		g.rotate(Math.toRadians(player.getAng()+90),player.getX(), player.getY());
		
		
		g.drawImage(img2, (int)player.getX() - img2.getWidth()/2, (int)player.getY() - img2.getHeight()/2, img2.getWidth(), img2.getHeight(), canvas);
		
		g.translate((int)player.getX(), (int)player.getY());
		
		g.drawString("paliwo: " + GameEngine.fuelRate + "%", -65, 380);
		
		g.translate(-canvas.getWidth()/2, -canvas.getHeight()/2);
		
		bs.show();
		
	}
	public BufferedImage getImg() {
		return img;
	}
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
		GameEngine.running = false;
		StartWindow.cmd="Czerwony";
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		frame.dispose();
		
	}
}
