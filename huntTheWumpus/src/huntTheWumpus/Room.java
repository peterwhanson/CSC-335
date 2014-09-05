package huntTheWumpus;

import java.awt.Point;

public class Room 
{
	private boolean blood;
	private boolean slime;
	private boolean goop;
	private boolean wumpus;
	private boolean hunter;
	private boolean bat;
	private boolean explored;
	private boolean pit;
	
	Room()
	{
		blood = false;
		slime = false;
		goop = false;
		wumpus = false;
		hunter = false;
		bat = false;
		explored = true;  //CHANGE THIS TO MAKE THINGS GO AWAY
	}
	
	public void enableBlood()
	{
		if(slime)
		{
			goop = true;
			slime = false;
			return;
		}
		blood = true;
	}
	
	public void enableSlime()
	{
		if(blood)
		{
			goop = true;
			blood = false;
			return;
		}
		slime = true;
	}
	
	public void enablePit()
	{
		pit = true;
	}
	
	public void enableWumpus()
	{
		wumpus = true;
	}
	
	public void enableExplored()
	{
		explored = true;
	}
	
	public void enableBat()
	{
		bat = true;
	}
	
	public void enableHunter()
	{
		hunter = true;
	}
	
	public void disableHunter()
	{
		hunter = false;
	}
	
	public String toString() //Generates the letter that represents the room
	{
		if(explored)
			if(hunter)
				return "O";
			else if(wumpus)
				return "W";
			else if(pit)
				return "P";
			else if(blood)
				return "B";
			else if(slime)
				return "S";
			else if(goop)
				return "G";
			else
				return " ";
		else
			return "X";
	}

}
