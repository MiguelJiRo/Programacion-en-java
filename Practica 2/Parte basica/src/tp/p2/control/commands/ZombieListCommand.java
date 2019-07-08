package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class ZombieListCommand extends Command{

	public ZombieListCommand() {
		super("zombielist", "[Z]ombie[L]ist", "Show zombie list.");
	}
	
	@Override
	public void execute(Game game, Controller controller) {
		game.commandZombieList();
		controller.setNoUpdate();
		controller.setNoPrintGame();
	}
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("zl") 
				|| commandWords[0].equalsIgnoreCase("zombielist")) 
		{			
			comando = this;					
		}	
		return comando;
	}

}

