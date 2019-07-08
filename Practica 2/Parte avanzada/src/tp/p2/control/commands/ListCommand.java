package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class ListCommand extends Command{

	public ListCommand() {
		super("list", "[L]ist", "Show plant list.");
	}
	
	@Override
	public void execute(Game game, Controller controller) {
		game.commandList();
		controller.setNoUpdate();
		controller.setNoPrintGame();
	}
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("l") 
				|| commandWords[0].equalsIgnoreCase("list")) 
		{			
			comando = this;					
		}	
		return comando;
	}

}
