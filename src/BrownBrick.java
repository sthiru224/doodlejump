import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BrownBrick extends Brick {
	Creature c; 
	
	boolean notBroken;
	BufferedImage brownBrick;
	BufferedImage brknBrownBrick;
	public BrownBrick(int x, int y) {
		super(x, y);
		notBroken = true;
		openImage();
	}

	public void openImage() {
		try {
			brownBrick =  ImageIO.read(getClass().getResource("brownB.png"));
			brknBrownBrick = ImageIO.read(getClass().getResource("brknBrownBrick.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void breakApart(){
		//		if(c.getY() == this.getY()-1) { //creature is on top left corner of the brick 
		//			return true; //should break apart 
		//		}
		//		else {
		//			return false; 
		//		}

		notBroken = false;
	}

	public void draw(Graphics g) {
		
		if (notBroken)
		{	g.drawImage(brownBrick, super.getX(),super.getY(),null);}
		else {
			g.drawImage(brknBrownBrick, super.getX(),super.getY(),null);
		}
		
	}

	


}
