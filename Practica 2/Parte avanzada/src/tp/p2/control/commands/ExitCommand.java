package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		System.out.println("Game Over");
		controller.setFinalizar();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("e") 
				|| commandWords[0].equalsIgnoreCase("exit")) 
		{			
			comando = this;					
		}	
		return comando;
	}
}