import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Creature {
	static int xLoc; 
	int yLoc; 
	static boolean faceLeft;
	Image IjumpRight;
	Image IjumpLeft;
	public final static int nWIDTH = 800;
	public final static int nHEIGHT = 600;
	
	public Creature(int x, int y) {
		xLoc = x;
		yLoc = y;
		openImage();
		faceLeft = false;
	}
	
	public void outOfThePanel() {
		if (xLoc > nWIDTH) {
			xLoc = 0; 
		}
		if (xLoc < 0) {
			xLoc = nWIDTH -1; 
			
		}
	}
	
	private void openImage() {
		try {
			IjumpRight =  ImageIO.read(getClass().getResource("jRight.png"));
			IjumpLeft = ImageIO.read(getClass().getResource("jLeft.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getX() {
		return xLoc; 
	}
	public int getY() {
		return yLoc; 
	}
	
	public void higherY(int numY) {
		yLoc-= numY;
	}
	
	public void lowerY(int numY) {
		yLoc+= numY;
	}
	
	public static void left() {
		faceLeft = true;
		xLoc-= 20;
	}
	
	public static void right() {
		faceLeft = false;
		xLoc+= 20;
	}
	
	
public void draw(Graphics g) {
	
	if (faceLeft)
	{	
	g.drawImage(IjumpLeft, xLoc, yLoc, 75, 75,                        null);}
	else {
		g.drawImage(IjumpRight, xLoc,yLoc, 75, 75, null);
	}
	}


}
