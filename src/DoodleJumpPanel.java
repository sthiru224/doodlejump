import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class DoodleJumpPanel  extends JPanel { 
	private BufferedImage background; 
	private BufferedImage jump; 
	private BufferedImage jumpLeft;
	private BufferedImage brownBrick; 
	private int yPosition;
	private boolean goDown = true;
	static Creature cret;
	//static Creature c2;
	ArrayList<Brick> brickArr = new ArrayList<Brick>();
	public final static int nWIDTH = 800;
	public final static int nHEIGHT = 600;
	//test
	// ********
	//static BlueBrick y;

	public DoodleJumpPanel() throws IOException {
		cret = new Creature(DoodleJumpPanel.nWIDTH/2,DoodleJumpPanel.nHEIGHT - 5);
		background = ImageIO.read(getClass().getResource("background.png"));
		jump = ImageIO.read(getClass().getResource("jRight.png"));
		jumpLeft = ImageIO.read(getClass().getResource("jLeft.png"));
		brownBrick =  ImageIO.read(getClass().getResource("brownB.png"));

	}

	Timer timer1 = new Timer(100, null);
	Timer timerMakeBrick = new Timer(200, null);




	public static void main(String[] args) throws IOException {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Welcome to Doodle Jump!!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		DoodleJumpPanel dp = new DoodleJumpPanel();
		frame.add(dp);
		dp.setPreferredSize(new Dimension(800,600));
		frame.pack();
		frame.setVisible(true);
		dp.setUpKeyMappings();
		dp.startGame();

	//	cret = new Creature(DoodleJumpPanel.nWIDTH/2,DoodleJumpPanel.nHEIGHT - 5);
		//c2 = new Creature(DoodleJumpPanel.nWIDTH/2,DoodleJumpPanel.nHEIGHT - 5);


		//test
		// ********
		//y = new BlueBrick(DoodleJumpPanel.nWIDTH/2, 40);

	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit left arrow!!");
				Creature.left();
			}
		});
		this.requestFocusInWindow();


		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the right arrow is pressed?
				System.out.println("Hit right arrow!!");
				Creature.right();
			}
		});
		this.requestFocusInWindow();

	}
	////   make for right and what happens if left is pressed
	//make bricks in spread y directions
	////   make boolean of turn right turn left
	////   change pic accordinly (cut new pick)
	////   change x and y position
	//   make blue keep moving left and right
	//   see if touching <  > then green jump and brown falls and breaks (changes brkn boolean)
	//   spring(+=10 instead of ++)
	//   constantly make new blocks and look like going up
	//   if touching then jump, else fall 
	// add monster
	//generate new bricks (randomly choose which brick and which location)
	//make it shoot at things
	//fix creature, put in it class, not panel
	//make jump in class
	//fix jumping stuff, with actual current y locations
	//add volume
	//remove genBrick from timer



	private void startGame() {
		timer1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}

		});
		yPosition = 200;
		timer1.start();
		genBrick(1);








		timerMakeBrick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isTouching()) {
					adjust_createB();
				}
			}

		});

		timerMakeBrick.start();

	}


	public void genBrick(int type) {
		// if type 1, then its at the very beginning and make bricks everywhere
		//if type 2, then its in middle of game when adjusting blocks so you need to make only at top
		
		int randNum;
		System.out.println("got to generating random number");
		
		// !!!!!!! make bricks in spread y directions
		//greenBrick


		if(type == 1)
		{
			for(int count1 = 0; count1<4; count1++) {
				randNum = (int) (Math.random() * 12);
				if(randNum <=4) {
					//check if need to add all specific methods of child classes to brick class
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy =  (int) (Math.random() * DoodleJumpPanel.nHEIGHT+60) - 60;
					Brick x = new GreenBrick(randx,randy );
					brickArr.add(x);
					//GreenBrick.draw(null);
				}
				else if(randNum<=7)
				{  //blueBrick
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy =  (int) (Math.random() * DoodleJumpPanel.nHEIGHT+60) - 60;
					Brick x = new BlueBrick(randx,randy );
					brickArr.add(x);
				}
				else if(randNum<=8) {
					//spring
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy = (int) (Math.random() * DoodleJumpPanel.nHEIGHT+60) -60;
					Brick x = new GreenBrickSpring(randx,randy );
					brickArr.add(x);
				}
				else {
					//brown
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy =  (int) (Math.random() * DoodleJumpPanel.nHEIGHT+60) - 60;
					Brick x = new BrownBrick(randx,randy );
					brickArr.add(x);
				}

			}

		}

		else if(type == 2) {

			for(int count2 = 0; count2<1; count2++) {
				randNum = (int) (Math.random() * 12);
				if(randNum <=4) {
					//check if need to add all specific methods of child classes to brick class
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy = 40 + (int) (Math.random() * 200) ;
					Brick x = new GreenBrick(randx,randy );
					brickArr.add(x);
					//GreenBrick.draw(null);
				}
				else if(randNum<=7)
				{  //blueBrick
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy = 40 + (int) (Math.random() * 200) ;
					Brick x = new BlueBrick(randx,randy );
					brickArr.add(x);
				}
				else if(randNum<=8) {
					//spring
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy = 40 + (int) (Math.random() * 200) ;
					Brick x = new GreenBrickSpring(randx,randy );
					brickArr.add(x);
				}
				else {
					//brown
					int randx = 50 + (int) (Math.random() * DoodleJumpPanel.nWIDTH-120);
					int randy = 40 + (int) (Math.random() * 200) ;
					Brick x = new BrownBrick(randx,randy );
					brickArr.add(x);
				}

			}
		}
	}

	protected void tick() {

		

		if (goDown) { //as going Down, yPosition increases
			cret.lowerY(20);
		}
		else {
			cret.higherY(20); //going up, yPos decreases
		}

		//!!!!! to check if its touching a brick and should move bricks down and make new bricks might not go here
		// should check if its touching something after it finishes the descent of jump, like goes 5 down
		// never mind nvm, maybe it should always check it, like in timer
		//if (cret.getY() >= 200 ) {
		if(isTouching())
		{
			goDown = false; //decrease yPos (go up) 
		}
//		else { 
//			goDown = true;
//			endGame();
//		}

		

		if (cret.getY() <= 0) {
			goDown = true; //increase yPos (go down) 
		}
		
		cret.outOfThePanel();
		
		repaint(); 
	}

	private void adjust_createB() {
		for(Brick b: brickArr) {
			b.lowerY();
		}


		genBrick(2);



	}









	private void endGame() {
		// TODO Auto-generated method stub
		//display score;
		//fall down (already done I think)

	}
	public boolean isTouching() {
		boolean ret = false;
		//only true if it is touching a brick, which means it can go ahead and jump up again

		for(Brick b : brickArr) {
			if(cret.getX()-80 < b.getX() && b.getX() < cret.getX()+80 && cret.getY()+80 > b.getX() && b.getX() > cret.getX()-80)
			{  ret = true; }


		}
		return ret;

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! work on this
		// goes up every time it touches a new brick thats higher, and it moves all current bricks lower 
		//and creates new bricks on top
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0 , 0,800, 600,this);
		//g.drawImage(jump, 350, yPosition, 100, 100, this);
		//g.drawImage(brownBrick, 50, 50, 70, 40,  this); 
		//test

		// ********
		//y.draw(g);


		for(Brick b: brickArr) {
			b.draw(g);
			System.out.println(b.getX());

		}
		cret.draw(g);
		

	}

}