//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a blueprint to create Room objects which contain information
//about the state of the rooms in the game's map such as if it contains the hunter, pits, wumpus, goo, etc.



package huntTheWumpus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	private boolean vArrow;
	private boolean hArrow;
	private boolean shotHimself;
	private char hunterDir;
	
	Room()
	{
		blood = false;
		slime = false;
		goop = false;
		wumpus = false;
		hunter = false;
		bat = false;
		explored = false;  //CHANGE THIS TO MAKE THINGS GO (AWAY)
	}
	
	public void disableElse()
	{
		slime = false;
		goop = false;
		blood = false;
		bat = false;
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
		disableElse();
	}
	
	public void enableWumpus()
	{
		wumpus = true;
		disableElse();
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
	
	public void enableVArrow()
	{
		vArrow = true;
	}
	
	public void enableHArrow()
	{
		hArrow = true;
	}
	public void disableVArrow()
	{
		vArrow = false;
	}
	
	public void disableHArrow()
	{
		hArrow = false;
	}
	
	public void enableShotHimself()
	{
		shotHimself = true;
	}
	
	public String toString() //Generates the letter that represents the room
	{
		if(explored)
			if(hunter)
				return "O";
			else if(vArrow)
				return "|";
			else if(hArrow)
				return "-";
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

	public boolean containsHunter() {
		if(hunter)
			return true;
		return false;
	}

	public boolean containsWumpus() {
		if(wumpus)
			return true;
		return false;
	}

	public boolean containsPit() {
		if(pit)
			return true;
		return false;
	}
	
	public boolean containsBlood() {
		if(blood)
			return true;
		return false;
	}
	
	public boolean containsSlime() {
		if(slime)
			return true;
		return false;
	}
	
	public boolean containsGoop() {
		if(goop)
			return true;
		return false;
	}

	public boolean containsBat() {
		if(bat)
			return true;
		return false;
	}
	
	public JLabel getImage()
	{
		if(explored)
			if(hunter)
			{
				if(shotHimself)
				{
					return new JLabel(new ImageIcon(tryImage("images/hunterShotHimself.png")));
				}
				if(pit)
				{
					return new JLabel(new ImageIcon(tryImage("images/hunterFellIn.png")));
				}
				if(wumpus)
				{
					return new JLabel(new ImageIcon(tryImage("images/hunterGotAte.png")));
				}
				if(goop)
				{
					switch(hunterDir)
					{
						case 'u': 
							return new JLabel(new ImageIcon(tryImage("images/goopyRoomHunterNorth.png")));
						case 'd':
							return new JLabel(new ImageIcon(tryImage("images/goopyRoomHunterSouth.png")));
						case 'l':
							return new JLabel(new ImageIcon(tryImage("images/goopyRoomHunterWest.png")));
						case 'r':
							return new JLabel(new ImageIcon(tryImage("images/goopyRoomHunterEast.png")));
						default:
							return new JLabel(new ImageIcon(tryImage("images/goopyRoomHunterWest.png")));
					}
				}
				if(slime)
				{
					switch(hunterDir)
					{
						case 'u': 
							return new JLabel(new ImageIcon(tryImage("images/slimyRoomHunterNorth.png")));
						case 'd':
							return new JLabel(new ImageIcon(tryImage("images/slimyRoomHunterSouth.png")));
						case 'l':
							return new JLabel(new ImageIcon(tryImage("images/slimyRoomHunterWest.png")));
						case 'r':
							return new JLabel(new ImageIcon(tryImage("images/slimyRoomHunterEast.png")));
						default:
							return new JLabel(new ImageIcon(tryImage("images/slimyRoomHunterWest.png")));
					}
				}
				if(blood)
				{
					switch(hunterDir)
					{
						case 'u': 
							return new JLabel(new ImageIcon(tryImage("images/bloodyRoomHunterNorth.png")));
						case 'd':
							return new JLabel(new ImageIcon(tryImage("images/bloodyRoomHunterSouth.png")));
						case 'l':
							return new JLabel(new ImageIcon(tryImage("images/bloodyRoomHunterWest.png")));
						case 'r':
							return new JLabel(new ImageIcon(tryImage("images/bloodyRoomHunterEast.png")));
						default:
							return new JLabel(new ImageIcon(tryImage("images/bloodyRoomHunterWest.png")));
					}
				}
				switch(hunterDir)
				{
					case 'u': 
						return new JLabel(new ImageIcon(tryImage("images/hunterNorth.png")));
					case 'd':
						return new JLabel(new ImageIcon(tryImage("images/hunterSouth.png")));
					case 'l':
						return new JLabel(new ImageIcon(tryImage("images/hunterWest.png")));
					case 'r':
						return new JLabel(new ImageIcon(tryImage("images/hunterEast.png")));
					default:
						return new JLabel(new ImageIcon(tryImage("images/hunterWest.png")));
				}
			}
			else if(vArrow)
			{
				return new JLabel(new ImageIcon(tryImage("images/hunterWest.png")));
			}
			else if(hArrow)
			{
				return new JLabel(new ImageIcon(tryImage("images/hunterWest.png")));
			}
			else if(wumpus)
			{
				return new JLabel(new ImageIcon(tryImage("images/wumpus.png")));
			}
			else if(pit)
			{
				return new JLabel(new ImageIcon(tryImage("images/slimePit.png")));
			}
			else if(blood)
			{
				return new JLabel(new ImageIcon(tryImage("images/bloodyRoom.png")));
			}
			else if(slime)
			{
				return new JLabel(new ImageIcon(tryImage("images/slimyRoom.png")));
			}
			else if(goop)
			{
				return new JLabel(new ImageIcon(tryImage("images/goopyRoom.png")));
			}
			else
			{
				return new JLabel(new ImageIcon(tryImage("images/emptyRoom.png")));
			}
		else
		{
			return new JLabel(new ImageIcon(tryImage("images/darkRoom.png")));
		}
	}
	
	public void setDir(char dir)
	{
		hunterDir = dir;
	}
	
	private BufferedImage tryImage(String url)
	{
		try 
		{
			return ImageIO.read(new File(url));
		}
		catch(IOException err)
		{
			err.printStackTrace();
		}
		return null;
	}

}
