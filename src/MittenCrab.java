
public class MittenCrab extends Crab {
	private static final long serialVersionUID = 103L;
	/**
	 * Constructor, parameters are the same as the superclass'
	 * @param xPosition
	 * @param yPosition
	 * @param xVelocity
	 * @param yVelocity
	 * @param gameState
	 */
	MittenCrab(double xPosition, double yPosition, double xVelocity, double yVelocity, GameState gameState){
		super(xPosition, yPosition, xVelocity, yVelocity, gameState);
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * Adds a new mitten crab to the game
	 */
	@Override
	public void crabClone() {
		getGameState().add( new MittenCrab(getxPosition(),getyPosition(),-getxVelocity(), -getyVelocity(),gameState));
		getGameState().addMessage("m");
		getGameState().mittenCount+=1;
	}
	

}
