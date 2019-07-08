package tp.p3.main;

import tp.p3.control.Controller;
import tp.p3.logic.Level;

public class PlantsVsZombies {
	
	public static void main(String[] args) {
		Level level;
		int seed = 1;
		if(args.length >= 1 && args.length <= 2) 
		{		
			level = Level.fromParamL(args[0].toLowerCase());
			if (level == null)
				System.err.print("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
			else 
			{
				try {
					if(args.length == 2)
						seed = Integer.parseInt(args[1]);	
					Controller c = new Controller(level,seed);
					c.run();
				}
				catch(NumberFormatException n) {
					System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number");
				}
			}
		}
		else {
			System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
		}		
	}
}