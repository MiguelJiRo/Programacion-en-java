package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class PrintModeCommand extends Command{

	private boolean release;
	private boolean debug;
	
	public PrintModeCommand() {
		super("printmode", "[P]rintMode", "Change print mode [Release|Debug].");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.setNoUpdate();
		game.changePrintMode(this.release,this.debug);		
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("p") 
				|| commandWords[0].equalsIgnoreCase("printmode")) 
		{		
			if(commandWords.length != 2)
				throw new CommandParseException("Incorrect number of arguments for printmode command: [P]rintMode release|debug");
			
			if(commandWords[1].equalsIgnoreCase("release")) {
				this.release = true;
				this.debug = false;
				comando = this;	
			}
			else if(commandWords[1].equalsIgnoreCase("debug")) {
				this.release = false;
				this.debug = true;
				comando = this;	
			}
			else
				throw new CommandParseException("Unknown print mode: " + commandWords[1]);
		}	
		return comando;
	}

}
