package tp.p3.control.commands;

import tp.p3.control.factory.ZombieFactory;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.zombies.*;
public class AddZombieCommand extends Command {
	
	private int fila;
	private int columna;
	private String zombieName;
	
	public AddZombieCommand(){
		super("addzombie", "[A]dd[Z]ombie", "Add a new zombie.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		Zombie zombie = ZombieFactory.getZombie(zombieName);
		if(game.addZombieToGame(zombie, fila, columna))
			return true;
		else
			return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("az") 
				|| commandWords[0].equalsIgnoreCase("addzombie")) {
			
			if(commandWords.length != 4) 
				throw new CommandParseException("Incorrect number of arguments for addzombie command: [A]dd[Z]ombie <zombie> <x> <y>");
			
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
				throw new CommandParseException("Unknown zombie name: " + commandWords[1]);
			
			if(commandWords[2].matches("\\d*") 
					&& commandWords[3].matches("\\d*"))
			{
				int f = Integer.parseInt(commandWords[2]);
				int c = Integer.parseInt(commandWords[3]);
		
				if(0 <= f && f < 4 /*controller.getF()*/ 
						&& 0 <= c && c < 4 /*controller.getC()*/)
				{	
					comando = this;
					this.fila = f;
					this.columna = c;
				}
				else
					throw new CommandParseException("Failed to addzombie " + this.zombieName + ": (" + f +", " + c + ") is an invalid position");
			}
			else
				throw new CommandParseException("Invalid argument for addzombie command, number expected: [A]dd[Z]ombie <zombie> <x> <y>");
		}		
		return comando;
	}
}
