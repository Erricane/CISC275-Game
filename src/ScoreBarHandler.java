import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScoreBarHandler extends Handler {
	/**
	 * Image handling for the scoreboard.
	 */
	
	int BlueCarbScore = gameState.getBlueCount();
	int TrashScore = gameState.getTrashCount();

	int red;
	int green;
	int blue;
	int b = -2;
	int prescoreA = 250;
	int prescoreB = 250;
	int countB = 0;
	int countA = 0;
	int temp;
	int missreduce;

	int missreducetemp;
	int win;
	int lose;
	int mittencrabcount;
	int mittencrabdisplaycount;
	int bluecrabcount;

	int bluecrabdisplaycount;
	int trashmisscount;
	int trashmissdisplaycount;
	int trashincount;
	int trashindisplaycount;

	ScoreBarHandler(GameScreen gameScreen, GameState gameState) {
		super(gameScreen, gameState);

	}

	/**
	 * Draw food in transit if visible If it hits a crab, it will disappear and
	 * replicate the crab
	 */
	@Override
	public void paint(Graphics g) {
		// System.out.println("blue"+gameState.getBlueCount());
		// System.out.println("trash"+gameState.getTrashCount());
		// System.out.println("bluedisplay"+BlueCarbScore);
		// System.out.println("miss"+gameState.gettrashMissed());
		// change();
		///////////////////////////// background
		g.setColor(Color.RED);
		g.setFont(new Font("Tahoma", Font.BOLD, 16 * Math.min(GameScreen.WIDTH / 1920,GameScreen.HEIGHT / 1080)));
		g.drawString("BlueCarb", (950 - 260) * GameScreen.WIDTH / 1920, (25 - 11) * GameScreen.HEIGHT / 1080);
		g.drawString("Trash", (950 + 230)  * GameScreen.WIDTH / 1920, (25 - 11)  * GameScreen.HEIGHT / 1080);
		g.setColor(Color.BLACK);
		g.fillRect((950 - 292) * GameScreen.WIDTH / 1920, (25 - 2)  * GameScreen.HEIGHT / 1080,
				(250 + 250 + 43 + 40) * GameScreen.WIDTH / 1920, 35  * GameScreen.HEIGHT / 1080);
		g.setColor(Color.BLACK);
		g.drawRect((950 - 292) * GameScreen.WIDTH / 1920, (25 - 2)  * GameScreen.HEIGHT / 1080,
				(250 + 250 + 43 + 40) * GameScreen.WIDTH / 1920, 35  * GameScreen.HEIGHT / 1080);

		/////////////////////////////////////

		// left side score bar.

		g.setColor(Color.RED);
		MissReduce();

		temp = ReduceL(250 - gameState.getMittenCount() * 10) - missreduce;
		lose = 250 - gameState.getMittenCount() * 10 + gameState.getBlueCount() * 20 - missreduce;
		g.fillRect((950 - 290 + 250 - temp) * GameScreen.WIDTH / 1920, 25  * GameScreen.HEIGHT / 1080, (temp) * GameScreen.WIDTH / 1920,
				32  * GameScreen.HEIGHT / 1080);
		warning(lose);
		g.setColor(new Color(red, green, blue));

		g.fillRect((950 - 290 + gameState.getMittenCount() * 10 + missreduce
				- gameState.getBlueCount() * 20) * GameScreen.WIDTH / 1920, 25  * GameScreen.HEIGHT / 1080, (lose) * GameScreen.WIDTH / 1920, 32  * GameScreen.HEIGHT / 1080);

		// right side score bar.
		g.setColor(Color.RED);
		g.fillRect((950 + 40 * GameScreen.WIDTH / 1920), 25 * GameScreen.HEIGHT / 1080,
				ReduceR(250 - gameState.getTrashCount() * 30)* GameScreen.WIDTH / 1920, 32 * GameScreen.HEIGHT / 1080);

		g.setColor(Color.YELLOW);
		win = 250 - gameState.getTrashCount() * 30;
		g.fillRect((950 + 40) * GameScreen.WIDTH / 1920, 25 * GameScreen.HEIGHT / 1080, (win) * GameScreen.WIDTH / 1920, 32 * GameScreen.HEIGHT / 1080);// gameState.getBlueCount()*10,
																									// 32);

		/////////////////////////////////// K.O.
		g.setColor(Color.BLACK);
		g.fillRect((950 - 40) * GameScreen.WIDTH / 1920, 0, 80 * GameScreen.WIDTH / 1920, 80  * GameScreen.HEIGHT / 1080);
		g.setColor(Color.WHITE);
		g.draw3DRect((950 - 40) * GameScreen.WIDTH / 1920, 0, 80 * GameScreen.WIDTH / 1920, 80  * GameScreen.HEIGHT / 1080, true);
		g.setFont(new Font("Tahoma", Font.BOLD, 32 * Math.min(GameScreen.WIDTH / 1920,GameScreen.HEIGHT / 1080)));
		g.setColor(Color.RED);
		g.drawString("K.O.", (950 - 30) * GameScreen.WIDTH / 1920, (25 + 25)* GameScreen.HEIGHT / 1080 );
		/////////////////////////////////
		g.setColor(Color.RED);
		g.setFont(new Font("Tahoma", Font.BOLD, 24 * Math.min(GameScreen.WIDTH / 1920,GameScreen.HEIGHT / 1080)));
		
	
		if (gameState.getMessages().indexOf('m') >= 0) {
			//mittencrabcount = gameState.getMittenCount();
			mittencrabdisplaycount = 100;
		} else if (mittencrabdisplaycount > 0) {
			mittencrabdisplaycount--;
			if (trashmissdisplaycount == 0 && bluecrabdisplaycount == 0 && trashindisplaycount == 0) {
				g.drawString("Don't feed the Mitten Crabs. They're an invasive species!", (950 + 230)  * GameScreen.WIDTH / 1920,
						(25 + 60) * GameScreen.HEIGHT / 1080);
			}
		}

		if (gameState.getMessages().indexOf('b') >= 0) {
			bluecrabcount = gameState.getBlueCount();
			bluecrabdisplaycount = 100;
		} else if (bluecrabdisplaycount > 0) {
			bluecrabdisplaycount--;
			if (trashmissdisplaycount == 0 && mittencrabdisplaycount == 0 && trashindisplaycount == 0) {
				g.drawString("You Fed A Blue Claw Crab. Great Job!", (950 - 260)  * GameScreen.WIDTH / 1920,
						(25 + 60) * GameScreen.HEIGHT / 1080);
			}
		}
		if (gameState.getMessages().indexOf('T') >= 0) {
			trashmisscount = gameState.gettrashMissed();
			trashmissdisplaycount = 100;
		} else if (trashmissdisplaycount > 0) {
			trashmissdisplaycount--;
			if (bluecrabdisplaycount == 0 && mittencrabdisplaycount == 0 && trashindisplaycount == 0) {
				g.drawString("Throw Trash In The Trash Can!", (950 - 330)  * GameScreen.WIDTH / 1920,
						(25 + 75) * GameScreen.HEIGHT / 1080);
			}
		}
		if (gameState.getMessages().indexOf('t') >= 0) {
			trashincount = gameState.getTrashCount();
			trashindisplaycount = 100;
		} else if (trashindisplaycount > 0) {
			trashindisplaycount--;
			if (bluecrabdisplaycount == 0 && mittencrabdisplaycount == 0 && trashmissdisplaycount == 0) {
				g.drawString("Nice Shot!", (950 + 240)  * GameScreen.WIDTH / 1920,
						(25 + 90) * GameScreen.HEIGHT / 1080);
			}
		}

				///////////////////////////////////

		if (win <= 0) {
			gameState.setiswin(true);
			gameState.setisend(true);
		}
		if (lose <= 0) {
			gameState.setisend(true);
			gameState.setiswin(false);
		}
	}

	public void warning(int a) {

		if (a < 50) {
			red = 255;

			blue = 0;
			if (green <= 10) {
				b = 10;
			} else if (green >= 245) {
				b = -10;
			}
			green = green + b;
		} else {
			red = green = 255;
			blue = 0;
		}

	}

	public int ReduceR(int a) {
		if (a < prescoreB) {
			countB++;
			if (countB > 20) {
				countB = 0;
				prescoreB--;
			}
			return prescoreB;

		}
		prescoreB = a;
		return 0;
	}

	public int ReduceL(int a) {
		if (a < prescoreA) {
			countA++;
			if (countA > 20) {
				countA = 0;
				prescoreA--;
			}
			return prescoreA;

		}
		prescoreA = a;
		return prescoreA;
	}

	public void MissReduce() {
		int number = 50;
		missreducetemp = missreducetemp + gameState.gettrashMissed();

		if (missreducetemp > number) {
			missreducetemp = 0;
			missreduce++;

		}

	}
}
