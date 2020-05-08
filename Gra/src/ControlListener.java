import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlListener implements KeyListener {

	private GameEngine engine;
	private boolean upKey = false;
	private boolean downKey = false;
	
	public ControlListener(GameEngine engine) {
		this.engine = engine;
		engine.getFrame().getCanvas().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP) upKey =true;
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) downKey =true;

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP) upKey =false;
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) downKey =false;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isUpKey() {
		return upKey;
	}

	public boolean isDownKey() {
		return downKey;
	}


}
