import java.awt.Graphics;

public abstract class Brick {
	int	x; 
	int y; 
	public Brick(int x, int y) {
		this.x = x; 
		this.y = y; 
	}
	public int getX() {
		return x; 
	}

	public int getY() {
		return y; 
	}
	
	public void incX() {
		x+=20;
	}
	
	public void decX() {
		x-=20;
	}
	
	public void lowerY() {
		y+=40;
	}
	
	
	
	
	public void openImage() {}
	public void tick() {}
	public void breakApart() {}
	
	
public void draw(Graphics G) {
		System.out.println("using brick draw method");
	}
}
