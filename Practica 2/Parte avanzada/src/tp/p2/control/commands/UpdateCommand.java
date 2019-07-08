package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class UpdateCommand extends NoParamsCommand{

	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		
	}

}
