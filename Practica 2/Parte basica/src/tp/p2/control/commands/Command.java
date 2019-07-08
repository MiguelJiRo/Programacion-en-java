package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

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
	
	public abstract void execute(Game game, Controller controller);
	public abstract Command parse(String[] commandWords, Controller controller);
	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
}
