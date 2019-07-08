package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class ZombieListCommand extends Command{

	public ZombieListCommand() {
		super("zombielist", "[Z]ombie[L]ist", "Show zombie list.");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.commandZombieList();
		game.setNoUpdate();
		// false para que no imprima el tablero
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;		
		if(commandWords[0].equalsIgnoreCase("zl") 
				|| commandWords[0].equalsIgnoreCase("zombielist")) 
		{
			if(commandWords.length != 1)
				throw new CommandParseException("Zombielist command has no arguments");
			comando = this;					
		}	
		return comando;
	}

}

