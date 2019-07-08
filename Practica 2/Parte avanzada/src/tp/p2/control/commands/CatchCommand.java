package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class CatchCommand extends Command{
	
	private int fila;
	private int columna;
	
	public CatchCommand() {
		super("catch", "[C]atch", "Catch a sun.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		if(game.getBoolSunTurno()) {
			if(game.catchSun(this.fila, this.columna))
				game.alternarBoolSunTurno(false);
			else {
				controller.setNoUpdate();
				controller.setNoPrintGame();
			}
		}
		else
			System.err.println("Oh something went wrong! Only 1 sun/turn!");
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("c") 
				|| commandWords[0].equalsIgnoreCase("catch")) 
		{
			int f = Integer.parseInt(commandWords[1]);
			int c = Integer.parseInt(commandWords[2]);
			// -1 para no añadir en la última columna
			if(0 <= f && f < controller.getF() 
					&& 0 <= c && c < controller.getC())
			{
				comando = this;
				this.fila = f;
				this.columna = c;
			}
		}
		return comando;
	}
}
