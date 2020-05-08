import java.awt.image.DataBufferInt;

public class Renderer {

	private int pW, pH;
	private int[] p;
	
	public Renderer(GameEngine ge) {
		pW = ge.getWidth();
		pH = ge.getHeight();
		p = ((DataBufferInt)ge.getFrame().getImg().getRaster().getDataBuffer()).getData();
	}
	public void clear() {
		for(int i= 0; i < p.length; i++) {
			p[i] = 0;
		}
	}
}
