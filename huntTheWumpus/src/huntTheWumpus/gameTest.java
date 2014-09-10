//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to test and insure that the classes in this package are working properly with
//Junit testing.

//No longer needed, game has a main method.

package huntTheWumpus;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest 
{
	
	//initialization: hunter started on slime once, we must prevent this
	//hunter has to start on a "safe spot"

	@Test
	public void testRoom()
	{
		Map map = new Map();
		System.out.println(map.toString());
	}

}