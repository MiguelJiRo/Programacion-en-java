package tp.p2.control.commands;

import tp.p2.control.Controller;

public class CommandParser {
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new AddZombieCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new ZombieListCommand(),
			new UpdateCommand(),
			new PrintModeCommand()
			};
	public static Command parseCommand(String[ ] commandWords, Controller controller) {
		Command comando = null;
		boolean continuar = true;
		int i = 0;
		while(i < availableCommands.length && continuar)
		{
			if(commandWords != null 
					&& commandWords.length >= 1)
			{
				comando = availableCommands[i].parse(commandWords, controller);
				if(comando != null)
					continuar = false;
				else
					++i;
			}
		}
		return comando;
	}
	
	public static String commandHelp() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < availableCommands.length; ++i) {
			builder.append(availableCommands[i].helpText());
			builder.append("\n");
		}
		return builder.toString();
	}
}
