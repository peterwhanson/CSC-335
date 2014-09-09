//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a main method that actually runs the Hunt the Wumpus game using objects
//created from the other classes in this package. The turn based game updates are handled by a while loop. User
//input is received through a Scanner object to determine hunter movement and the current state of the map is
//displayed out to the system in text format.

package huntTheWumpus;

import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	
	public static void main(String[] args) throws IOException{
		
		Map gameMap = new Map();
		Hunter hunter = new Hunter(gameMap.getHunterLocation());
		
		
		boolean gameOver = false;
		boolean gameWon = false;
		boolean pitDeath = false;
		boolean wumpusDeath = false;
		String textMap;
		String logo;
		Scanner key = new Scanner(System.in);
		String movement;
		int roomMessageCode;
		
		 	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); 
				System.out.println("xx|  |XX|  |x|  |xx|  |x|  \\xx|  |x|       |xxx|        |x|  |XX|  |x|   ___|xx");
				System.out.println("xx|  |xx|  |x|  |xx|  |x|   \\x|  |x|       |xxx|        |x|  |XX|  |x|  |xxxxxx"); 
				System.out.println("xx|        |x|  |xx|  |x|    \\|  |xxx|   |xxxxxxxx|   |xxx|        |x|   __|xxx");
				System.out.println("xx|   __   |x|  |xx|  |x|  |\\    |xxx|   |xxxxxxxx|   |xxx|   __   |x|  |xxxxxx");
				System.out.println("xx|  |xx|  |x|        |x|  |x\\   |xxx|   |xxxxxxxx|   |xxx|  |xx|  |x|      |xx");
				System.out.println("xx|__|xx|__|xx\\______/xx|__|xx\\__|xxx|___|xxxxxxxx|___|xxx|__|xx|__|x|______|xx"); 
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
				System.out.println("xxxx|  |x^x|  |xx|  |xx|  |xxx/\\xxxxx/\\xxx|      \\xxx|  |xx|  |xxx/     \\xxxxxx" );
				System.out.println("xxxx|  |/ \\|  |xx|  |xx|  |xx/  \\xxx/  \\xx|  |x|  |xx|  |xx|  |xx|  /x\\__|xxxxx");
				System.out.println("xxxx|         |xx|  |xx|  |xx|   \\x/   |xx|  |x|  |xx|  |xx|  |xxx\\___ \\xxxxxxx");
				System.out.println("xxxx|    /\\   |xx|  |xx|  |xx|    v    |xx|   ___/xxx|  |xx|  |xxxxxxx\\ \\xxxxxx" );
				System.out.println("xxxxx\\  /xx\\  /xx|  |xx|  |xx|  |\\ /|  |xx|  |xxxxxxx|  |xx|  |xx| \\xx/  |xxxxx" );
				System.out.println("xxxxxx\\/xxxx\\/xxxx\\______/xxx|  |xvx|  |xx|__|xxxxxxxx\\______/xxxx\\_____/xxxxxx" );
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		
	//	System.out.println(logo);
		
		String desc = "You are a brave hunter searching for the elusive wumpus in a cave containing"
					+ "\nbottomless pits and warped space-time properties. Slime is one square away"
					+ "\nfrom the pits, blood is up to 2 squares away from the wumpus. Goo is the "
					+ "\nmixture of both. You only have one arrow to shoot, if you miss you are dead." + 
				"\n\nPress 'u' to move up, 'd' to move down, 'r' to move right, 'l' to move left, \nand 'a' to shoot your arrow. Good luck!\n\n\nPress Enter to play.\n\n";
		for(int i = 0; i < desc.length(); i++)
		{
			System.out.print(desc.charAt(i));
			wait(50); //Delay between character prints
		}
		
		key.nextLine();
		
		while(!gameOver)
		{

			
			//this is my attempt at clearing the text in the console. In theory, it should work for the windows 7 command line at least, but does nothing in Eclipse
			//Feel free to remove.
//			String OS = System.getProperty("os.name");
//			//Check to see if returned String contains "Windows"
//			System.out.println(OS);
//			if (OS.contains("Windows") == true)
//				Runtime.getRuntime().exec("cmd /c cls");
//			else
//				Runtime.getRuntime().exec("clear");
			
			
			textMap = gameMap.toString();
			System.out.print(textMap);
			
			roomMessageCode = gameMap.roomMessage();
			
			switch(roomMessageCode)
			{
			case 0: System.out.println("You are in an empty room.");
					break;
			case 1: System.out.println("You are in a room filled with blood.");
					break;
			case 2: System.out.println("You are in a room filled with goop.");
					break;
			case 3: System.out.println("You are in a room filled with slime.");
					break;
			case 4: System.out.println("You have fallen into a bottomless pit!! Enviornmental damage too stronk!");
					pitDeath = true;
					break;
			case 5: System.out.println("You come face to face with the Wumpus and barely have time to panic\n"
					+ "before it rips you to pieces!");
					wumpusDeath = true;
					break;
			case 6: System.out.println("You are in a room filled with bats."); //Not used in this version
					break;
			}
					
			if(pitDeath || wumpusDeath)
			{
				gameOver = true;
				gameMap.clearFog();
				textMap = gameMap.toString();
				System.out.print(textMap);
				break;
			}
			
			System.out.println("You are facing " + hunter.getOrientation());
			System.out.println("Where would you like to move?");
			movement = key.next();
			
			switch(movement)
			{
			case "u": hunter.moveUp();
					  gameMap.updateHunter(hunter.getPosition());
					  break;
			case "d": hunter.moveDown();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "l": hunter.moveLeft();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "r": hunter.moveRight();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "a": gameMap.initialArrow(hunter.getPosition());
					  while(gameMap.animateArrow(hunter.getOrientation()))
					  {
							System.out.print(gameMap.toString());
					    	wait(100);
					  }
					  gameOver = true;  
					  gameWon = gameMap.updateArrow(hunter.shootArrow(), hunter.getPosition());  
			          break;
			default:  System.out.println("Not a valid key, try again. Pick either u,d,l,r, or a.");  
			}
		}
		
		
		key.close();
		
		if(gameWon){
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out.println("You won! One shot, one kill, no luck, all skill.");
		}
		if(!gameWon && !wumpusDeath && !pitDeath)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out.println("You lost, you shot yourself in the back with your arrow!");
		}
	}
	
	public static void wait(int time)
	{
		try 
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
