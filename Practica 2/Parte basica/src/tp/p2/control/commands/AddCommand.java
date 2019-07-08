package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.control.factory.PlantFactory;
import tp.p2.logic.Game;
import tp.p2.logic.plants.*;
public class AddCommand extends Command {
	
	private int fila;
	private int columna;
	private String plantName;
	
	public AddCommand(){
		super("add", "[A]dd", "Add a new plant.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		Plant plant = PlantFactory.getPlant(plantName);
		game.addPlantToGame(plant, fila, columna);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("a") 
				|| commandWords[0].equalsIgnoreCase("add")) {
			if(commandWords[2].matches("\\d*") 
					&& commandWords[3].matches("\\d*"))
			{
				int f = Integer.parseInt(commandWords[2]);
				int c = Integer.parseInt(commandWords[3]);
				// -1 para no añadir en la última columna
				if(0 <= f && f < controller.getF() 
						&& 0 <= c && c < controller.getC()-1)
				{
					comando = this;
					this.fila = f;
					this.columna = c;
					if(commandWords[1].equalsIgnoreCase("s") 
							|| commandWords[1].equalsIgnoreCase("sunflower")) 
						this.plantName = "sunflower";
					else if(commandWords[1].equalsIgnoreCase("p") 
							|| commandWords[1].equalsIgnoreCase("peashooter")) 
						this.plantName = "peashooter";
					else if(commandWords[1].equalsIgnoreCase("n") 
							|| commandWords[1].equalsIgnoreCase("wallnut")) 
						this.plantName = "wallnut";
					else if(commandWords[1].equalsIgnoreCase("c") 
							|| commandWords[1].equalsIgnoreCase("cherrybomb")) 
						this.plantName = "cherrybomb";
				}
			}
		}		
		return comando;
	}
}
