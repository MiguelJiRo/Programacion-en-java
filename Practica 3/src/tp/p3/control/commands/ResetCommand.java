package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class ResetCommand extends Command{

	public ResetCommand() {
		super("reset", "[R]eset", "Starts a new game.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.inicializarList();
		game.setCiclos(0);
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("r") 
				|| commandWords[0].equalsIgnoreCase("reset")) 
		{	
			if(commandWords.length != 1)
				throw new CommandParseException("Reset command has no arguments");
			comando = this;					
		}	
		return comando;
	}
}
