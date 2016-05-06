import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	
public class ShooterHandler extends Handler implements KeyListener {
	public static final int arrowWidth = 228;
	public static final int arrowHeight = 214;
	int xSpeed;
	int ySpeed;
	int degrees;
	int power;
	int randProj;
	Food tempf;
	Trash tempt;
	Projectile projectile;
	BufferedImage arrow_image;
	Image powerbar_image;
	
	ShooterHandler(GameScreen gameScreen,GameState gameState) {
		super(gameScreen,gameState);
		// TODO Auto-generated constructor stub
		try{
			arrow_image = ImageIO.read(new File("img/arrow.png"));
			powerbar_image = ImageIO.read(new File("img/powerbar.png"));
		}
		catch(IOException e)
		{
			System.out.println("Arrow Image not found");
		}
		gameScreen.addKeyListener(this);
		generateProjectile();
	}

	public void paint(Graphics g) {
		GameState game = getGameState();
		Shooter gameShooter = getGameState().getShooter();
		
		PowerBar pb = gameShooter.getPowerBar();
		Arrow a = gameShooter.getArrow();
		
		// TODO rotate arrow - NEEDS TESTING -----------------------------------------------------
        double rotation_needed = Math.toRadians(90 - a.getDirectFluc());
		double locationX = (double) arrowWidth / 2;
		double locationY = (double) arrowHeight / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotation_needed, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
		BufferedImage new_image = op.filter(arrow_image, null);
		
		g.drawImage(new_image, (int)a.xPosition, (int)a.yPosition, new Color(0, 0, 0, 0), null);
		
		//g.drawImage(powerbar_image, (int)pb.xPosition, (int)pb.yPosition, new Color(0, 0, 0, 0), null);
	}

	/**
	 * Fetch angle and power of shot
	 * Use trig functions to set xSpeed and ySpeed
	 * If power is less than half, trash will not make it to trash, leaves it on board (does not affect food)
	 * If trash, add to trash collection. Need to keep track of those that are still on the board
	 * Rest arrow movement
	 * @param gameShooter
	 */
	public void calculateTrajectory(Shooter gameShooter) {
		degrees = gameShooter.getArrow().getDirection();
		power = gameShooter.getPowerBar().getPower();
		
		xSpeed = (int) (Math.toDegrees(Math.cos(Math.toRadians(degrees)))/10);
		ySpeed = -(int) (Math.toDegrees(Math.sin(Math.toRadians(degrees)))/10);
		
		projectile.setVelocity(xSpeed, ySpeed);
		
		if (projectile instanceof Trash && power < 50) {
			projectile.setEnd((int) (400 + Math.random()*200));
		}
		
		/*if (projectile instanceof Trash) {
			gameState.addTrash(projectile);
		}*/
		
		gameShooter.getArrow().setStop();
		
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			Shooter gameShooter = getGameState().getShooter();
			if(gameShooter.getFlag()){
				gameShooter.getArrow().setDirection();
				gameShooter.getArrow().setStop();
				gameShooter.changeFlag();
				
			}
			else{
				gameShooter.getPowerBar().setPower();
				System.out.print("Fired at: " + gameShooter.getPowerBar().getPower() + " power ");
				System.out.println("in direction " + gameShooter.getArrow().getDirection() + "!");
				
				calculateTrajectory(gameShooter);
				generateProjectile();
				
				gameShooter.changeFlag();
				gameShooter.setDefault();
			}
		}
	}
	
	public void generateProjectile()
	{
		if (Math.random() < .5) {
			projectile = new Food(480,700,0,0,gameState);
		}
		else {
			projectile = new Trash(480,700,0,0,gameState);
		}
		gameState.add(projectile);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
