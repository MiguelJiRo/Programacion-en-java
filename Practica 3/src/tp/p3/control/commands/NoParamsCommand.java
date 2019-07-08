package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public abstract class NoParamsCommand extends Command {
		
	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	public abstract boolean execute(Game game) throws CommandExecuteException;
	public Command parse(String[] commandWords) throws CommandParseException{
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
