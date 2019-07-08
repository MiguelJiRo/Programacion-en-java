package tp.p2.main;

import tp.p2.control.Controller;
import tp.p2.logic.Level;

public class PlantsVsZombies {
	
	public static void main(String[] args) {
		int seed = 1;
		Level level;
		if((args[0] != null) && (args.length > 0))
		{
			level = Level.fromParamL(args[0].toLowerCase());
			if (level == null)
				level = Level.EASY;
			if(args.length > 1)
				if((args[1] != null) && (args[1].matches("\\d*")) && (Integer.parseInt(args[1]) > 0))
					seed = Integer.parseInt(args[1]);
		}
		else
			level = Level.EASY;
		Controller c = new Controller(level,seed);
		c.run();
	}
}