import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlListener implements KeyListener {

	private GameEngine engine;
	private boolean upKey = false;
	private boolean downKey = false;
	private boolean leftKey = false;
	private boolean rightKey = false;
	
	public ControlListener(GameEngine engine) {
		this.engine = engine;
		engine.getFrame().getCanvas().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP) upKey =true;
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) downKey =true;
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) leftKey =true;
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) rightKey =true;

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP) upKey =false;
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) downKey =false;
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) leftKey = false;
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) rightKey =false;

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

	public boolean isLeftKey() {
		return leftKey;
	}

	public boolean isRightKey() {
		return rightKey;
	}


}
