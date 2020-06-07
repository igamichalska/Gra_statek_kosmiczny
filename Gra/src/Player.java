
public class Player {
	
	private int x;
	private int y;
	private int vX;
	private int vY;
	private int aX;
	private int aY;
	private double ang;
	
	public double calcDistSqr(Planet p) {
		return Math.pow( (this.x-p.getX())^2 + (this.y - p.getY())^2, 0.5 );
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getvX() {
		return vX;
	}
	public void setvX(int vX) {
		this.vX = vX;
	}
	public int getvY() {
		return vY;
	}
	public void setvY(int vY) {
		this.vY = vY;
	}
	public int getaX() {
		return aX;
	}
	public void setaX(int aX) {
		this.aX = aX;
	}
	public int getaY() {
		return aY;
	}
	public void setaY(int aY) {
		this.aY = aY;
	}

	public double getAng() {
		return ang;
	}

	public void setAng(double ang) {
		this.ang = ang;
	}
}
