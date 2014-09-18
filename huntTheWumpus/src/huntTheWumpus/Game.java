//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a main method that actually runs the Hunt the Wumpus game using objects
//created from the other classes in this package. The turn based game updates are handled by a while loop. User
//input is received through a Scanner object to determine hunter movement and the current state of the map is
//displayed out to the system in text format.

package huntTheWumpus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Game extends JFrame
{
	
	private static Map gameMap; //ALL PUBLIC VARIABLES ARE USED ONLY FOR JUNIT TESTING (Would not appear in real game)
	private static boolean pitDeath;
	private static boolean wumpusDeath;
	private static boolean gameOver;
	private static boolean gameWon;
	private static Hunter hunter;
	private wumpusGUI gui;

	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
	public Game()
	{
		gameMap = new Map();
		hunter = new Hunter(gameMap.getHunterLocation());

		gameOver = false;
		gameWon = false;
		pitDeath = false;
		wumpusDeath = false;
		String textMap;
		Scanner key = new Scanner(System.in);
		char movement;
		int roomMessageCode;
		
		gui = new wumpusGUI(gameMap.getHunterLocation());

//		// This prints the 'graphic' for the game, before it starts
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); 
//		System.out.println("xx|  |XX|  |x|  |xx|  |x|  \\xx|  |x|       |xxx|        |x|  |XX|  |x|   ___|xx");
//		System.out.println("xx|  |xx|  |x|  |xx|  |x|   \\x|  |x|       |xxx|        |x|  |XX|  |x|  |xxxxxx");
//		System.out.println("xx|        |x|  |xx|  |x|    \\|  |xxx|   |xxxxxxxx|   |xxx|        |x|   __|xxx");
//		System.out.println("xx|   __   |x|  |xx|  |x|  |\\    |xxx|   |xxxxxxxx|   |xxx|   __   |x|  |xxxxxx");
//		System.out.println("xx|  |xx|  |x|        |x|  |x\\   |xxx|   |xxxxxxxx|   |xxx|  |xx|  |x|      |xx");
//		System.out.println("xx|__|xx|__|xx\\______/xx|__|xx\\__|xxx|___|xxxxxxxx|___|xxx|__|xx|__|x|______|xx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println("xxxx|  |x^x|  |xx|  |xx|  |xxx/\\xxxxx/\\xxx|      \\xxx|  |xx|  |xxx/     \\xxxxxx");
//		System.out.println("xxxx|  |/ \\|  |xx|  |xx|  |xx/  \\xxx/  \\xx|  |x|  |xx|  |xx|  |xx|  /x\\__|xxxxx");
//		System.out.println("xxxx|         |xx|  |xx|  |xx|   \\x/   |xx|  |x|  |xx|  |xx|  |xxx\\___ \\xxxxxxx");
//		System.out.println("xxxx|    /\\   |xx|  |xx|  |xx|    v    |xx|   ___/xxx|  |xx|  |xxxxxxx\\ \\xxxxxx");
//		System.out.println("xxxxx\\  /xx\\  /xx|  |xx|  |xx|  |\\ /|  |xx|  |xxxxxxx|  |xx|  |xx| \\xx/  |xxxxx");
//		System.out.println("xxxxxx\\/xxxx\\/xxxx\\______/xxx|  |xvx|  |xx|__|xxxxxxxx\\______/xxxx\\_____/xxxxxx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
//
//		// The description of the game, and instructions
//		String desc = "You are a brave hunter searching for the elusive wumpus in a cave containing" 
//				+ "\nbottomless pits and warped space-time properties. Slime is one square away"
//				+ "\nfrom the pits, blood is up to 2 squares away from the wumpus. Goo is the "
//				+ "\nmixture of both. You only have one arrow to shoot, if you miss you are dead."
//				+ "\n\nPress 'u' to move up, 'd' to move down, 'r' to move right, 'l' to move left, \nand 'a' to shoot your arrow. Good luck!\n\n\nPress Enter to play.\n\n";
//		
//		// Makes the characters of the
//		// description appear on the
//		// screen like an rpg, rather
//		// than just showing up
//		for (int i = 0; i < desc.length(); i++) 
//		{
//			System.out.print(desc.charAt(i));
//			wait(50); // Delay between character prints
//		}
//
//		key.nextLine(); // Forces the player to press enter to play
//		textMap = gameMap.toString(); // Prints initial map layout
//		System.out.print(textMap);

		while (!gameOver)
		{
			// If the player has fallen in a pit, clear the fog
			// and let them know
			if (pitDeath)
			{
				gameOver = true;
				gameMap.clearFog();
				textMap = gameMap.toString();
				System.out.print(textMap);
				System.out.println("You have fallen into a bottomless pit!! Enviornmental damage too stronk!");
				break;
			} 
			// If the player has run into the Wumpus,
			// clear the fog and let them know
			else if (wumpusDeath) 
			{
				gameOver = true;
				gameMap.clearFog();
				textMap = gameMap.toString();
				System.out.print(textMap);
				System.out
						.println("You come face to face with the Wumpus and barely have time to panic\n"
								+ "before it rips you to pieces!");
				break;
			} else
			// Since the player is "safe", tell them where they are
			{
				roomMessageCode = gameMap.roomMessage();
				switch (roomMessageCode)
				{
				case 0:
					System.out.println("You are in an empty room.");
					break;
				case 1:
					System.out.println("You are in a room filled with blood.");
					break;
				case 2:
					System.out.println("You are in a room filled with goop.");
					break;
				case 3:
					System.out.println("You are in a room filled with slime.");
					break;
				case 4:
					pitDeath = true;
					break;
				case 5:
					wumpusDeath = true;
					break;
				case 6:
					//Not used in this version
					System.out.println("You are in a room filled with bats."); 
					break;
				}

			}
			
			// If the player is still alive, ask them where they want to go
			if (!pitDeath && !wumpusDeath) 
			{
				System.out.println("You are facing " + hunter.getOrientation());
				System.out.println("Where would you like to move?");
				movement = key.next().charAt(0);
				switch (movement)
				{
				case 'u':
					hunter.moveUp();
					gameMap.updateHunter(hunter.getPosition());
					break;
				case 'd':
					hunter.moveDown();
					gameMap.updateHunter(hunter.getPosition());
					break;
				case 'l':
					hunter.moveLeft();
					gameMap.updateHunter(hunter.getPosition());
					break;
				case 'r':
					hunter.moveRight();
					gameMap.updateHunter(hunter.getPosition());
					break;
				case 'a':
					gameMap.initialArrow(hunter.getPosition());
					while (gameMap.animateArrow(hunter.getOrientation())) //animates the arrow before resolving the result
					{
						System.out.print(gameMap.toString());
						wait(100);
					}
					gameOver = true;
					gameWon = gameMap.updateArrow(hunter.shootArrow(),
							hunter.getPosition());
					break;
				default:
					System.out
							.println("Not a valid key, try again. Pick either u,d,l,r, or a.");
				}
			}

			textMap = gameMap.toString();
			System.out.print(textMap);
		}

		key.close();

		//Player has won, time to celebrate!!
		if (gameWon)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out
					.println("You won! One shot, one kill, no luck, all skill.");
		}
		
		//Player took an arrow to the back. Of the knee. Tell them of their failure.
		if (!gameWon && !wumpusDeath && !pitDeath)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out
					.println("You lost, you shot yourself in the back with your arrow!");
		}
	}
	
	private void drawMap()
	{
		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
			{
				
				ImageIcon empty = new ImageIcon("images/EmptyRoom.png");
				
			}
	}


	// A simplified delay timer, so that the
	// try/catch doesn't have to take up
	// space.
	public static void wait(int time) 
	{
		try
		{
			Thread.sleep(time);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
