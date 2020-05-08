import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameEngine implements Runnable {

	GamePanel panel;
	private GameFrame frame;
	
	private Thread thread;
	private boolean running;
	private final double MAXUPDATE = 1.0/30.0;
	
	private Player player;
	private int fuel;
	
	
	private ControlListener control;
	
	private final double GRAV = 1;
	private final double ACCF = 0.1;
	private final double ACCB = 0.5;
	private final double ROT = 0.1;
	
	private int width =320, height = 240;
	private float scale = 3f;
	
	
	List<Planet> planets = new ArrayList<Planet>();
	
	public GameEngine(GameFrame panel){
		super();
		//this.frame = panel;
		
		
	}
	public GameEngine(){
		super();
	}
	
	public void start() {
		frame = new GameFrame();
		
		planets.add(new Planet(400, 20, 0, 0.004, 10, Color.blue));
		planets.add(new Planet(0, 50, 0, 0, 40, Color.green));
		planets.add(new Planet(300, 20, 1, 0.004, 10, Color.blue));
		planets.add(new Planet(200, 30, 0, 0.008, 1, Color.green));
		player = new Player(100,100);
		thread = new Thread(this);
		control = new ControlListener(this);
		thread.start();
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
		

		//System.out.println(accX);
		
		while(running) {
			
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			

			
			
			while(unprocessedTime >= MAXUPDATE) {
				unprocessedTime -= MAXUPDATE;
				
				accX = 0;
				accY = 0;
				
				for (Planet pr: planets) {
					pr.setAng(pr.getAng() + pr.getAngV());
					accX += GRAV * pr.getMass() * (pr.getX() - player.getX()) / Math.pow(player.calcDist(pr), 3);
					accY += GRAV * pr.getMass() * (pr.getY() - player.getY()) / Math.pow(player.calcDist(pr), 3);
				}
				
				if(control.isLeftKey()) {
					player.setAng(player.getAng() - ROT);
				}
				if(control.isRightKey()) {
					player.setAng(player.getAng() + ROT);
				}
				if(control.isUpKey() && fuel>=0) {
					accX += ACCF* Math.cos(player.getAng());
					accY += ACCF*Math.sin(player.getAng());
					fuel--;
				}
				if(control.isDownKey()&& fuel>=0) {
					accX -= ACCB* Math.cos(player.getAng());
					accY -= ACCB*Math.sin(player.getAng());
					fuel--;
				}
				
				player.setaX(accX);
				player.setaY(accY);
				
				player.setvX(player.getvX() + player.getaX());
				player.setvY(player.getvY() + player.getaY());
				
				player.setX(player.getX() + player.getvX());
				player.setY(player.getY() + player.getvY());
				
				render = true;
				
				frames ++;
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("accx: " + accX);
					System.out.println("accY: " + accY);
					System.out.println("angle  : " + Math.cos(player.getAng()));
				}
			}
			if(render) {
				frame.update(planets, player);
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
	public GameFrame getFrame() {
		return frame;
	}
}
