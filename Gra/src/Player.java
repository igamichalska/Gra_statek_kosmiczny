
public class Player {
	
	private double x;
	private double y;
	private double vX;
	private double vY;
	private double aX;
	private double aY;
	private double ang;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.ang = 0;
		this.aX = 0;
		this.aY = 0;
		this.vX = 0;
		this.vY = 0;
	}
	
	public double calcDist(Planet p) {
		return Math.pow( Math.pow((this.x-p.getX()),2) + Math.pow((this.y-p.getY()),2), 0.5);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getvX() {
		return vX;
	}
	public void setvX(double d) {
		this.vX = d;
	}
	public double getvY() {
		return vY;
	}
	public void setvY(double d) {
		this.vY = d;
	}
	public double getaX() {
		return aX;
	}
	public void setaX(double accX) {
		this.aX = accX;
	}
	public double getaY() {
		return aY;
	}
	public void setaY(double accY) {
		this.aY = accY;
	}

	public double getAng() {
		return ang;
	}

	public void setAng(double ang) {
		this.ang = ang;
	}
}
