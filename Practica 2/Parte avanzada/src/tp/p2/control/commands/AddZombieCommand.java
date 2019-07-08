package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.control.factory.PlantFactory;
import tp.p2.control.factory.ZombieFactory;
import tp.p2.logic.Game;
import tp.p2.logic.zombies.*;
public class AddZombieCommand extends Command {
	
	private int fila;
	private int columna;
	private String zombieName;
	
	public AddZombieCommand(){
		super("addzombie", "[A]dd[Z]ombie", "Add a new zombie.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		Zombie zombie = ZombieFactory.getZombie(zombieName);
		game.addZombieToGame(zombie, fila, columna);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords.length != 4)
			return null;
		if(commandWords[0].equalsIgnoreCase("az") 
				|| commandWords[0].equalsIgnoreCase("addzombie")) {
			if(commandWords[2].matches("\\d*") 
					&& commandWords[3].matches("\\d*"))
			{
				int f = Integer.parseInt(commandWords[2]);
				int c = Integer.parseInt(commandWords[3]);
		
				if(0 <= f && f < controller.getF() 
						&& 0 <= c && c < controller.getC())
				{
					comando = this;
					this.fila = f;
					this.columna = c;
					if(commandWords[1].equalsIgnoreCase("b") 
							|| commandWords[1].equalsIgnoreCase("buckethead")) 
						this.zombieName = "zombiecaracubo";
					else if(commandWords[1].equalsIgnoreCase("z") 
							|| commandWords[1].equalsIgnoreCase("zombie")) 
						this.zombieName = "zombiecommon";
					else if(commandWords[1].equalsIgnoreCase("r") 
							|| commandWords[1].equalsIgnoreCase("runner")) 
						this.zombieName = "zombiedeportista";
					else
						return null;
				}
				else
					return null;
			}
			else
				return null;
		}		
		return comando;
	}
}
