package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class HelpCommand extends Command{

	public HelpCommand() {
		super("help", "[H]elp", "Prints this help message.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		System.out.println(CommandParser.commandHelp());
		game.setNoUpdate();
		// false para que no imprima el tablero
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("h") 
				|| commandWords[0].equalsIgnoreCase("help")) 
		{	
			if(commandWords.length != 1)
				throw new CommandParseException("Help command has no arguments");
			comando = this;					
		}	
		return comando;
	}

}
