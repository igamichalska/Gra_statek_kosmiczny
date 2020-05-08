import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameEngine implements Runnable {

	GamePanel panel;
	private Thread thread;
	private boolean running;
	private final double MAXUPDATE = 1.0/60.0;
	private Player player;
	
	private int fuel;
	private boolean upKey = false;
	private boolean downKey = false;
	
	private final int GRAV = 9;
	private final int ACCF = 10;
	private final int ACCB = 5;
	
	private int width =320, height = 240;
	private float scale = 3f;
	private GameFrame frame;
	
	
	List<Planet> planets = new ArrayList<Planet>();
	
	public GameEngine(GamePanel panel){
		super();
		this.panel = panel;
		
		
	}
	public GameEngine(){
		super();
	}
	
	public void start() {
		frame = new GameFrame(this);
		
		planets.add(new Planet(0, 50, 0, 0, Color.green));
		planets.add(new Planet(100, 20, 0, 4, Color.blue));
		planets.add(new Planet(200, 60, 50, 20, Color.green));
		player = new Player();
		thread = new Thread(this);
		thread.run();
	}
	
	public void run() {

		running = true;
		fuel = 400;
		boolean render = false;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double accX = 0;
		double accY = 0;
		
		double frameTime = 0;
		double frames = 0;
		double fps = 0;
		
		while(running) {
			
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= MAXUPDATE) {
				unprocessedTime -= MAXUPDATE;
				
				for (Planet pr: planets) {
					pr.setAng(pr.getAng() + pr.getAngV());
					accX += GRAV*pr.getMass()*(pr.getX()-player.getX())/Math.pow(player.calcDistSqr(pr), 3);
					accX += GRAV*pr.getMass()*(pr.getY()-player.getY())/Math.pow(player.calcDistSqr(pr), 3);
				}
				
				if(upKey) {
					accX += ACCF*Math.cos(player.getAng());
					accY += ACCF*Math.sin(player.getAng());
					fuel--;
				}
				if(downKey) {
					accX -= ACCB*Math.cos(player.getAng());
					accY -= ACCB*Math.sin(player.getAng());
					fuel--;
				}
				
				player.setaX((int)accX);
				player.setaY((int)accY);
				
				player.setvX(player.getvX() + player.getaX());
				player.setvY(player.getvX() + player.getaY());
				
				player.setX(player.getX() + player.getvX());
				player.setY(player.getY() + player.getvY());
				
				render = true;
				
				frames ++;
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("fps: " + fps);
				}
			}
			if(render) {
				frame.update(planets);
			
				//panel.repaint();			
				//panel.display(planets);

				
				//System.out.println("render");
				//
				// 			panel.display(planets)
				//

				
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		dispose();
	}


	private void dispose() {
		// TODO Dispose actions
		
	}

	public int getFuel() {
		return fuel;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setUpKey(boolean upKey) {
		this.upKey = upKey;
	}

	public void setDownKey(boolean downKey) {
		this.downKey = downKey;
	}
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.start();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
}
