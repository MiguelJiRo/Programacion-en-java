package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class CatchCommand extends Command{
	
	private int fila;
	private int columna;
	
	public CatchCommand() {
		super("catch", "[C]atch", "Catch a sun.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean correcto = false;
		if(game.getBoolSunTurno()) {
			if(game.catchSun(this.fila, this.columna)) {
				game.alternarBoolSunTurno(false);
				correcto = true;
			}
		}
		else
			throw new CommandExecuteException("Oh something went wrong! Only 1 sun/turn!");
		
		return correcto;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("c") 
				|| commandWords[0].equalsIgnoreCase("catch")) 
		{
			if(commandWords.length != 3)
				throw new CommandParseException("Incorrect number of arguments for catch command: [C]atch <x> <y>");
			
			if(!commandWords[1].matches("\\d*") 
					|| !commandWords[2].matches("\\d*"))
				throw new CommandParseException("Invalid argument for catch command, number expected: [C]atch <x> <y>");
			
			int f = Integer.parseInt(commandWords[1]);
			int c = Integer.parseInt(commandWords[2]);
			if(0 <= f && f < 4 /*controller.getF()*/ 
					&& 0 <= c && c < 8 /*controller.getC()*/)
			{
				comando = this;
				this.fila = f;
				this.columna = c;
			}
			else 
				throw new CommandParseException("Failed to catch: (" + f +", " + c + ") is an invalid position");
		}
		return comando;
	}
}
