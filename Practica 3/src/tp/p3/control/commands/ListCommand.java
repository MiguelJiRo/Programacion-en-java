package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class ListCommand extends Command{

	public ListCommand() {
		super("list", "[L]ist", "Show plant list.");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.commandList();
		game.setNoUpdate();
		// false para que no imprima el tablero
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("l") 
				|| commandWords[0].equalsIgnoreCase("list")) 
		{	
			if(commandWords.length != 1)
				throw new CommandParseException("List command has no arguments");
			comando = this;					
		}	
		return comando;
	}

}
