package tp.p3.control.commands;

import tp.p3.control.factory.PlantFactory;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.plants.*;
public class AddCommand extends Command {
	
	private int fila;
	private int columna;
	private String plantName;
	
	public AddCommand(){
		super("add", "[A]dd", "Add a new plant.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		Plant plant = PlantFactory.getPlant(plantName);
		if(game.addPlantToGame(plant, fila, columna))
			return true;
		else
			return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		
		if(commandWords[0].equalsIgnoreCase("a") 
				|| commandWords[0].equalsIgnoreCase("add")) 
		{
			if(commandWords.length != 4) 
				throw new CommandParseException("Incorrect number of arguments for add command: [A]dd <plant> <x> <y>");
			
			if(commandWords[1].equalsIgnoreCase("s") 
					|| commandWords[1].equalsIgnoreCase("sunflower")) 
			{
				this.plantName = "sunflower";
			}
			else if(commandWords[1].equalsIgnoreCase("p") 
					|| commandWords[1].equalsIgnoreCase("peashooter")) 
			{
				this.plantName = "peashooter";
			}
			else if(commandWords[1].equalsIgnoreCase("n") 
					|| commandWords[1].equalsIgnoreCase("wallnut")) 
			{
				this.plantName = "wallnut";
			}
			else if(commandWords[1].equalsIgnoreCase("c") 
					|| commandWords[1].equalsIgnoreCase("cherrybomb")) 
			{
				this.plantName = "cherrybomb";
			}
			else
				throw new CommandParseException("Unknown plant name: " + commandWords[1]);
			
			if(commandWords[2].matches("\\d*") 
					&& commandWords[3].matches("\\d*"))
			{
				int f = Integer.parseInt(commandWords[2]);
				int c = Integer.parseInt(commandWords[3]);
				// -1 para no añadir en la última columna
				if(0 <= f && f < 4 /*controller.getF()*/ 
						&& 0 <= c && c < 8 - 1 /*controller.getC()-1*/)
				{
					comando = this;
					this.fila = f;
					this.columna = c;					
				}
				else 
					throw new CommandParseException("Failed to add " + this.plantName + ": (" + f +", " + c + ") is an invalid position");
			}
			else 
				throw new CommandParseException("Invalid argument for add command, number expected: [A]dd <plant> <x> <y>");
		}	
		
		return comando;
	}
}
