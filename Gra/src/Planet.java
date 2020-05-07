import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class Planet implements Runnable {

	private int r = 50;
	private int ang = 0;
	private int angV = 5;
    private int selfR = 20;
    private int mass;
    private Color color = Color.BLACK;
    private BufferedImage[] img = null;
    private int i = 0;
    private boolean running = true;
    
	public Planet(int r, int selfR, int ang, int angV, Color c) {
		this.r = r;
		this.selfR = selfR;
		this.ang = ang;
		this.angV = angV;
		this.color = c;
	}
	
	public int getX() {
		return (int) (r*Math.cos(ang));
	}
	
	public int getY() {
		return (int) (r*Math.sin(ang));
	}
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getAng() {
		return ang;
	}
	public void setAng(int ang) {
		this.ang = ang;
	}
	public int getAngV() {
		return angV;
	}
	public void setAngV(int angV) {
		this.angV = angV;
	}
	public int getSelfR() {
		return selfR;
	}
	public void setSelfR(int selfR) {
		this.selfR = selfR;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), 2*getSelfR(), 2*getSelfR());
        if (img != null && img.length > 0) {
            g.drawImage(img[i], r*(int)Math.cos(ang), r*(int)Math.sin(ang), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }
	public void run() {
		while (running) {
            i++;
            if (i >= img.length) {
                i = 1;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            	System.out.println(e.getMessage());
            }
        }
		
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

}
