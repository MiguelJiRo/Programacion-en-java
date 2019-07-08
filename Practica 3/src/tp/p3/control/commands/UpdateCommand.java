package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class UpdateCommand extends Command{

	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {		
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("") 
				|| commandWords[0].equalsIgnoreCase("none")) 
		{	
			if(commandWords.length != 1)
				throw new CommandParseException("None command has no arguments");
			comando = this;					
		}	
		return comando;
	}

}
