package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{		
		game.setFinalizar();
		System.out.print("Game over");
		// false para que no imprima el tablero
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("e") 
				|| commandWords[0].equalsIgnoreCase("exit")) 
		{		
			if(commandWords.length != 1)
				throw new CommandParseException("Exit command has no arguments");
			comando = this;					
		}	
		return comando;
	} 
}