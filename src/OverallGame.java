import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OverallGame extends JPanel{
	
	GameState gameState;
	JFrame screen;
	final static int frameWidth = 500;
    final static int frameHeight = 500;
    private Collection<ViewHandler> viewHandlerCollection;
    private Collection<InputHandler> inputHandlerCollection; //paints after
    /*
     * ImageBank contains a set of Sprite Set
     * SpriteSets contains a list of all Image Sets for a GameObject
     * Image Sets contains a list of Images for an animation of a GameObject
     *
     */
    
    public OverallGame(GameState gameState)
    {
    	this.screen = new JFrame("Super Team 6");
    	this.screen.setBackground(Color.gray);
		this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.screen.setSize(frameWidth, frameHeight);
		this.screen.setVisible(true);
		this.screen.getContentPane().add(this);
		this.gameState = gameState;	
		this.viewHandlerCollection = new LinkedList<ViewHandler>();
		this.inputHandlerCollection = new LinkedList<InputHandler>();
		
    }
	
	public void update()
	{
		for (InputHandler inputHandler: inputHandlerCollection)
		{
			inputHandler.update();
		}
		gameState.update();
		screen.repaint();
	}
	
	
	public void add(ViewHandler viewHandler)
	{
		viewHandlerCollection.add(viewHandler);
	}
	
	public void remove(ViewHandler viewHandler)
	{
		viewHandlerCollection.remove(viewHandler);
	}
	
	public void add(InputHandler inputHandler)
	{
		inputHandlerCollection.add(inputHandler);
	}
	
	public void remove(InputHandler inputHandler)
	{
		inputHandlerCollection.remove(inputHandler);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		for (ViewHandler viewHandler : viewHandlerCollection )
		{
			viewHandler.paint(g);
		}
		for (InputHandler inputHandler : inputHandlerCollection)
		{
			inputHandler.paint(g);
		}
	}
	
	public JFrame getScreen() {
		return screen;
	}

	public void setScreen(JFrame screen) {
		this.screen = screen;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public Collection<ViewHandler> getViewHandlerCollection() {
		return viewHandlerCollection;
	}

	public Collection<InputHandler> getInputHandlerCollection() {
		return inputHandlerCollection;
	}


}
