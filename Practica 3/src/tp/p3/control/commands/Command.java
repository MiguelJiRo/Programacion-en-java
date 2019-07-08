package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public abstract class Command {
	//Para que lo puedan ver solo 
	//sus descendientes utilizamos protected
	protected String helpText;
	protected String commandText;
	protected final String commandName;
	
	public Command(String commandText, String commandInfo, String helpInfo) 
	{
		this.commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
}
