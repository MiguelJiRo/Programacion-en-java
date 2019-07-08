package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class PrintModeCommand extends Command{

	public PrintModeCommand() {
		super("printmode", "[P]rintMode", "Change print mode [Release|Debug].");
	}

	@Override
	public void execute(Game game, Controller controller) {
		controller.setNoUpdate();
		controller.changePrintMode();		
	}
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("p") 
				|| commandWords[0].equalsIgnoreCase("printmode")) 
		{			
			comando = this;					
		}	
		return comando;
	}

}
