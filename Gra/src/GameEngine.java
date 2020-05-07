import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameEngine implements Runnable {

	GamePanel panel;
	private Thread thread;
	private boolean running;
	private final double MAXUPDATE = 1.0/60.0;
	
	List<Planet> planets = new ArrayList<Planet>();
	
	public GameEngine(GamePanel panel){
		super();
		this.panel = panel;
	}
	
	public void start() {
		planets.add(new Planet(0, 50, 0, 0, Color.green));
		planets.add(new Planet(100, 20, 0, 4, Color.blue));
		planets.add(new Planet(200, 60, 50, 20, Color.green));
		thread = new Thread(this);
		thread.run();
		
		
	}
	
	public void run() {

		running = true;
		boolean render = false;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		while(running) {
			
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			
			while(unprocessedTime >= MAXUPDATE) {
				unprocessedTime -= MAXUPDATE;
				
				//TODO: Update
				for (Planet pr: planets) {
					pr.setAng(pr.getAng() + pr.getAngV());
				}
				render = true;
				
				
			}
			if(render) {
				
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
	

}
