package huntTheWumpus;

import java.awt.Point;
import java.util.Random;

public class Map 
{
	private Room[][] map;
	private Point wumpus;
	private Point hunter;
	private Point[] pit;
	private int horizSize = 10;
	private int vertSize = 10;
	
	Map()
	{
		map = new Room[horizSize][vertSize];
		Random randy = new Random();
		pit = new Point[randy.nextInt(3) + 3];
		
		System.out.println("Generated " + pit.length + " pits.");
		
		
		hunter = new Point(randy.nextInt(horizSize), randy.nextInt(vertSize));
		wumpus = new Point(randy.nextInt(horizSize), randy.nextInt(vertSize));
		
		for(int i = 0; i < pit.length; i++) 
		
			pit[i] = new Point(randy.nextInt(horizSize), randy.nextInt(vertSize));
		
		while(inRange(wumpus, hunter, 3)) //If the generated wumpus coords are less than 2 blocks away, re-randomize them.
			wumpus = new Point(randy.nextInt(horizSize), randy.nextInt(vertSize));
		
		for(Point point : pit) //Makes sure all the generated pits aren't sitting on top of each other or other objects (not including blood, goop, or slime)
		{
			int i = 0;
			boolean tooClose = false;
			for(int j = 0; j < i; j++)
				if(inRange(point, pit[j], 1))
					tooClose = true;
			while(inRange(point, hunter, 2) || inRange(point, wumpus, 1) || tooClose)
			{
				point = new Point(randy.nextInt(horizSize), randy.nextInt(vertSize));
				for(int j = 0; j < i; j++)
					if(inRange(point, pit[j], 1))
						tooClose = true;
			}
			
			i++;
		}
		
		
		System.out.println("Wumpus x coord: " + wumpus.x);  //Where da Wumpus at doe?
		System.out.println("Wumpus y coord: " + wumpus.y); 
		
		for(int j = 0; j < horizSize; j++) //Set all the attributes for all the "rooms." 
		{
			for(int i = 0; i < vertSize; i++)
			{
				map[j][i] = new Room();
				Point temp = new Point(j, i);
				if(hunter.equals(temp))
				{
					map[j][i].enableHunter();
					map[j][i].enableExplored();
				}
				if(wumpus.equals(temp)) 
				{
					map[j][i].enableWumpus();
				}
				if(inRange(temp, wumpus, 2))
				{
					map[j][i].enableBlood();
				}
				for(Point point : pit)
				{
					if(point.equals(temp))
						map[j][i].enablePit();
					if(inRange(temp, point, 1))
						map[j][i].enableSlime();
				}
			}
		}
	}
	
	public String toString() //Generates the string that is the map
	{
		String output = "";
		for(int j = 0; j < horizSize; j++)
		{
			for(int i = 0; i < vertSize; i++)
			{
				output += "[" + map[j][i] + "]";
			}
			output += "\n";
		}
		return output;
	}
	
	private boolean inRange(Point reference, Point interest, int distance) //Method for seeing if two points are within a certain distance, and wraps around
	{
		if(reference.distance(interest) <= distance) //Checks if the box in inherently close enough
		
			return true;
		
		
		Point temp = new Point(reference.x, reference.y);

		if(horizSize - reference.x <= distance ) //If the point is on either extreme, it simulates it being on the opposite end of the map
			
			temp.x -= horizSize;
		
		else if(reference.x <= distance )
			
			temp.x += horizSize;
		
		if(temp.distance(interest) <= distance)
		
			return true;
		
		
		temp.x = reference.x; //Set the x coordinate back, so that y is not affected by the previous movement of x to the other side
		
		if(vertSize - reference.y <= distance )
			
			temp.y -= vertSize;
		
		else if( reference.y <= distance )
			
			temp.y += vertSize;
		
		if(temp.distance(interest) <= distance)
		
			return true;
		
		return false;	
		
	}
}
