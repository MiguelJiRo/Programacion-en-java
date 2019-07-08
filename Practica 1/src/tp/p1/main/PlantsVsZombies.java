package tp.p1.main;

import tp.p1.control.Controller;
import tp.p1.logica.Level;

public class PlantsVsZombies {
	
	public static void main(String[] args) {
		int channel;
		Level level = Level.fromParamL(args[0].toLowerCase());
		if (level == null)
			level = level.EASY;
		// long seed - vía por donde va a circular el zombie
		if(args[1] == null)
			channel = 3;
		else
			channel = Integer.parseInt(args[1]);
		
		Controller c = new Controller(level,channel);
		c.run();
	}
}