package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class ResetCommand extends Command{

	public ResetCommand() {
		super("reset", "[R]eset", "Starts a new game.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.inicializarList();
		game.setCiclos(0);
	}
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("r") 
				|| commandWords[0].equalsIgnoreCase("reset")) 
		{			
			comando = this;					
		}	
		return comando;
	}
}
