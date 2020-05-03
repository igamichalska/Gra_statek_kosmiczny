import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class Planet implements Runnable {

	private int xPos = 50;
	private int yPos = 50;
    private int width = 20;
    private int height = 20;
    private Color color = Color.BLACK;
    private int vx = 0;
    private int vy = 0;
    private BufferedImage[] img = null;
    private int i = 0;
    private boolean running = true;
    
	public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillOval(xPos, yPos, getWidth(), getHeight());
        if (img != null && img.length > 0) {
            g.drawImage(img[i], xPos, yPos, new ImageObserver() {
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

}
