package tp.p3.control.commands;

import tp.p3.exceptions.CommandParseException;

public class CommandParser {
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new AddZombieCommand(),
			new CatchCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new ZombieListCommand(),
			new UpdateCommand(),
			new SaveCommand(),
			new LoadCommand(),
			new PrintModeCommand()
			};
	
	public static Command parseCommand(String[ ] commandWords) throws CommandParseException{
		Command comando = null;
		boolean continuar = true;
		int i = 0;
		while(i < availableCommands.length && continuar)
		{
			if(commandWords != null 
					&& commandWords.length >= 1)
			{
				comando = availableCommands[i].parse(commandWords);
				if(comando != null)
					continuar = false;
				else
					++i;
			}
		}
		
		if(comando == null && continuar)
			throw new CommandParseException("Unknown command. Use ’help’ to see the available commands");	
		
		return comando;
	}
	
	public static String commandHelp() {
		StringBuilder builder = new StringBuilder();
		builder.append("The available commands are:");
		for(int i = 0; i < availableCommands.length; ++i) {
			builder.append(availableCommands[i].helpText());
			builder.append("\n");
		}
		return builder.toString();
	}
}
