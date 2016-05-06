import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrashHandler extends Handler {

	Image image;
	
	TrashHandler(GameScreen gameScreen, GameState gameState) {
		super(gameScreen, gameState);
		try{
			image = ImageIO.read(new File("img/rock.png")).getScaledInstance(30, 30, Image.SCALE_FAST);	
		}
		catch(IOException e)
		{
			
		}
	}

	/**
	 * First draw all trash on the board
	 * Then draw trash currently in transit if visible
	 */
	@Override
	public void paint(Graphics g) {
		for (GameObject gameObject : getGameState().getGameObjectCollection()){
			if (gameObject instanceof Trash)
			{
				g.drawImage( image, (int)gameObject.xPosition, (int)gameObject.yPosition, new Color(0,0,0,0), null);
		
			}
		}
	}

}
