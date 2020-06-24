import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenBrickSpring extends GreenBrick  {
	Image Ispring;

	public GreenBrickSpring(int x, int y) {
		super(x, y);
		openImage();
	}
	
	public void openImage() {
		try {
			Ispring =  ImageIO.read(getClass().getResource("springg.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	
public void draw(Graphics g) {
	g.drawImage(Ispring, super.getX(),super.getY(),null);
	}


}
