import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class StartDisplayHandler extends Handler implements KeyListener {
	/**
	 * This is the starting image players will see when they start the game
	 */
	Image startdisplay; //image to be used for the start screen
	boolean active; // tells us if the start screen is needed or not
	/**
	 * consctuctor for the start display. Throws exception if images can't be read
	 * @param gameScreen
	 * @param gameState
	 */
	StartDisplayHandler(GameScreen gameScreen, GameState gameState) {
		super(gameScreen, gameState);
		try{
			startdisplay = ImageIO.read(new File("img/startscreen.png")).getScaledInstance((int)(GameScreen.WIDTH/1.5), (int)(GameScreen.HEIGHT/1.5), Image.SCALE_FAST);
		}
		catch (IOException e){
			System.out.println("startscreen.png not found");
		}
		gameScreen.addKeyListener(this);
		active = true;
	}
	/**
	 * paints graphics on screen
	 * @param g, graphics used
	 */
	@Override
	public void paint(Graphics g) {
		if(active){
			g.drawImage(startdisplay, GameScreen.WIDTH/6, GameScreen.HEIGHT/12, new Color(0,0,0,0), null);
		}
	}
	/**
	 * blank key event for typing keys
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}
	/**
	 * key event for pressing a key. Ends start display if it is active
	 * @param KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(active){
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				active = false;
			}
		}
	}
	/**
	 * blank key event for releasing key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
