
public class Food extends Projectile{

	Food(double xPosition, double yPosition, double xVelocity, double yVelocity, GameState gameState) {
		super(xPosition, yPosition, xVelocity, yVelocity, gameState);
		// TODO Auto-generated constructor stub
	}
	/**
	 * food becomes invisible if it goes "off screen"
	 */
	public void updateState() {
		if (xPosition <= 0 || xPosition >= gameState.frameWidth) {
			setVelocity(0,0);
			isVisible = false;
		}
		if (yPosition <= 0 || yPosition >= gameState.frameHeight) {
			setVelocity(0,0);
			isVisible = false;
		}
	}

}
