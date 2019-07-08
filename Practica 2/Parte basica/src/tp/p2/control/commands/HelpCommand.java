package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class HelpCommand extends Command{

	public HelpCommand() {
		super("help", "[H]elp", "Prints this help message.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		System.out.println(CommandParser.commandHelp());
		controller.setNoUpdate();
		controller.setNoPrintGame();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("h") 
				|| commandWords[0].equalsIgnoreCase("help")) 
		{			
			comando = this;					
		}	
		return comando;
	}

}
