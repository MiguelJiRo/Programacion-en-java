package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public abstract class NoParamsCommand extends Command {
		
	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	public abstract void execute(Game game, Controller controller);
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords.length == 0)
			return null;
		if(commandWords.length > 0)
			if(commandWords[0].equalsIgnoreCase(commandName)) {
				return this;
			}
			else
				return null;
		return null;
	}
}
