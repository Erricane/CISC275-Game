
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static java.lang.System.out;

public class powerbarHandler extends Handler implements ActionListener, KeyListener {

	JButton testButton;
	boolean red = true;
	int  length=32;
	boolean direction = true;
	public powerbarHandler(OverallGame overallGame) {
		
		super(overallGame);
		
		//adds a button to game
//		testButton = new JButton("Click me");
//		overallGame.add(testButton,BorderLayout.SOUTH);
//		overallGame.validate();
//		overallGame.repaint();
//		
//		testButton.addActionListener(this);
		overallGame.addKeyListener(this);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void  paint(Graphics g)
	{
		for (GameObject gameObject : getOverallGame().getGameState().getGameObjectCollection())
		{
			if (gameObject instanceof powerbarObject)
			{	
				if (length>=80){
				g.setColor(Color.RED);
				}
				else if (length >=45){
					g.setColor(Color.YELLOW);
				}else {
					g.setColor(Color.GREEN);
				}
				powerbar();
				g.fillRect((int)gameObject.getxPosition(),(int)gameObject.getyPosition(),length,32);
				
			}if (gameObject instanceof powerbarFrame)
			{
				g.setColor(Color.BLACK);
				
				g.fillRect((int)gameObject.getxPosition(),(int)gameObject.getyPosition(),132,36);
				
			}

		}
	}
	
	private void powerbar(){
		
		if (direction){
			length++;
			if (length>=128){
				direction=false;
			}
		}else {
			length--;
			if(length<=1){
				direction=true;
			}
		}
	}
	
	
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		red = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
