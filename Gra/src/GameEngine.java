import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class GameEngine implements Runnable {

	private GameFrame frame;
	
	private static Thread thread;
	static boolean running;
	private final double MAXUPDATE = 1.0/60.0;
	private Player player;
	
	private float fuel;
	private float maxFuel = 400;
	private double distMax;
	public static float fuelRate;
	
	private ControlListener control;
	
	private final double GRAV = 2;
	private final double ACCF = 0.08;
	private final double ACCB = 0.04;
	private final double ROT = 0.9;
	
	private int width =320, height = 240;
	private float scale = 3f;
	
	
	List<Planet> planets = new ArrayList<Planet>();
	List<Float> distance = new ArrayList<Float>();
	
	public GameEngine(){
		super();
	}
	
	public void start() {
		
		frame = new GameFrame();
		switch(StartWindow.lvl) {
		case 0:
			maxFuel = 500;
			distMax = 1.4;
			break;
		case 1:
			maxFuel = 400;
			distMax = 1.1;
			break;
		case 2:
			maxFuel = 300;
			distMax = 0.9;
			break;
		case 3:
			maxFuel = 250;
			distMax = 0.7;
			break;
		}
		
		
		planets.add(new Planet(0,100,6.28*Math.random(),0, 100, Color.orange)); //slonce
		planets.add(new Planet(250,10,6.28*Math.random(),0.009, 10, Color.red)); //merkury
		planets.add(new Planet(400, 35,6.28*Math.random(),0.0075, 80, Color.lightGray)); //wenus
		planets.add(new Planet(600, 40, 6.28*Math.random(),0.0045, 100, Color.blue)); //ziemia
		planets.add(new Planet(850, 20, 6.28*Math.random(),0.0045, 20, Color.red)); //mars
		planets.add(new Planet(1600, 300, 6.28*Math.random(),0.003, 900, Color.white)); //jowisz
		planets.add(new Planet(2200, 200, 6.28*Math.random(),0.002, 300, Color.gray)); //saturn
		planets.add(new Planet(2900, 100, 6.28*Math.random(),0.002, 150, Color.cyan)); //uran
		planets.add(new Planet(3200, 90, 6.28*Math.random(),0.002, 200, Color.blue)); //neptun
		
		Planet startPlanet = planets.get(StartWindow.startPlanetInd + 1);
		player = new Player(startPlanet.getX() + startPlanet.getSelfR() + 50, startPlanet.getY() + startPlanet.getSelfR() + 50);
		
		thread = new Thread(this);
		control = new ControlListener(this);
		thread.start();
	}
	
	public void run() {

		running = true;
		fuel = maxFuel;
		boolean render = false;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double accX = 0;
		double accY = 0;
		
		
		while(running) {
			
			fuelRate = ( ((int)(100*fuel)) / ((int)maxFuel));
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			
			
			while(unprocessedTime >= MAXUPDATE) {
				unprocessedTime -= MAXUPDATE;
				
				accX = 0;
				accY = 0;
				distance.clear();
				
				for (Planet pr: planets) {
					distance.add((float) player.calcDist(pr));
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
				if(control.isUpKey() && fuel>=1) {
					accX += ACCF* Math.cos(Math.toRadians(player.getAng()));
					accY += ACCF*Math.sin(Math.toRadians(player.getAng()));
					fuel--;
				}
				if(control.isDownKey()&& fuel>=0) {
					accX -= ACCB* Math.cos(Math.toRadians(player.getAng()));
					accY -= ACCB*Math.sin(Math.toRadians(player.getAng()));
					fuel--;
				}
				
				player.setaX(accX);
				player.setaY(accY);
				
				player.setvX(player.getvX() + player.getaX());
				player.setvY(player.getvY() + player.getaY());
				
				player.setX(player.getX() + player.getvX());
				player.setY(player.getY() + player.getvY());
				
				
				for (int i=0; i<planets.size(); i++) 
				{	
					Planet p = planets.get(i);
					
					if(player.calcDist(p)<= (p.getSelfR() + 20)) 
					{	
						double dist = distance.get(i) - player.calcDist(p);
						
						
						if(dist >=distMax || i != (StartWindow.endPlanetInd+1)) {
							frame.dispose();
							
							SwingUtilities.invokeLater(
									 new Runnable(){
										public void run() {
											GUI gui = new GUI("przegrana.png");
											gui.setLocationRelativeTo(null);
											gui.setVisible(true);
										}
									 }
								);
							
						} else {
							frame.dispose();
							
							
							SwingUtilities.invokeLater(
									 new Runnable(){
										public void run() {
											GUI gui = new GUI("wygrana.png");
											gui.setLocationRelativeTo(null);
											gui.setVisible(true);
										}
									 }
								);
							
						}
						running = false;
						
					}
				}
				
				
				render = true;
			}
		
		
			if(render) {
				frame.update(planets, player);
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void setRunning(boolean running) {
		GameEngine.running = running;
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
