//TODO add todos
//TODO create tests
//TODO add function comments and javadoc
//TODO add a button to the frame

import java.lang.*;

public class Launcher {


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameState gameState = new GameState();
		OverallGame overallGame = new OverallGame(gameState);
		Launcher.setupGameState(gameState);
		Launcher.setupOverallGame(overallGame);
		
		//TODO correct while loop goes here
		while (true)
		{
			overallGame.update();
			System.out.println(gameState);
		}
	}
	
	public static void setupGameState (GameState gameState)
	{
		//Construct and add Objects here
		gameState.add( (GameObject)( new TestObject(32,32,1,0,gameState) ) );
		gameState.add( (GameObject)( new TestObject(76,32,-5,0,gameState) ) );
	}
	
	public static void setupOverallGame (OverallGame overallGame)
	{
		//Construct and add Handlers here
		overallGame.add ( (ViewHandler) (new TestInputHandler(overallGame) ) );
	}
	
	public static void setupGameStateFromFile (GameState gameState)
	{
		//Construct and add Objects here
	}
	

}