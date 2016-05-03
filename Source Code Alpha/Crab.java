import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Crab extends GameObject {
	
	final static int crabUpperBound = 400;
	final static int crabLowerBound = 600;

	Crab(double xPosition, double yPositionition, double xVelocity, double yVelocity, GameState gameState) {
		super(xPosition, yPositionition, xVelocity, yVelocity, gameState);
		// TODO Auto-generated constructor stub
	}

	public void updateState() {
		if (xPosition > GameState.frameWidth)
		{
			xVelocity = - Math.abs(xVelocity);
		}
		if (xPosition < 0)
		{
			xVelocity = Math.abs(xVelocity);
		}
		if (yPosition > crabLowerBound)
		{
			yVelocity = - Math.abs(yVelocity);
		}
		if (yPosition < crabUpperBound) 
		{
			yVelocity = Math.abs(yVelocity);
		}
	}
	
	/**
	 * Checks if crab collided with projectile.
	 * @return 
	 * @return true if within a certain hitbox, false otherwise.
	 */
	public boolean checkCollision() {
		for (GameObject gameObject : getGameState().getGameObjectCollection())
			if (gameObject instanceof TestObject && Math.abs(this.xPosition - gameObject.xPosition) < 50 && Math.abs(this.yPosition - gameObject.yPosition) < 50) {
				System.out.println("Collision!");
				return true;
			}
		return false;
	}

}
