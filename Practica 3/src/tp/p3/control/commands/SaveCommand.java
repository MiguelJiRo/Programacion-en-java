package tp.p3.control.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.GameObject;
import tp.p3.logic.plants.Cherrybomb;
import tp.p3.logic.plants.Plant;
import tp.p3.logic.plants.Sunflower;
import tp.p3.logic.zombies.Zombie;
import tp.p3.logic.zombies.ZombieCaracubo;
import tp.p3.logic.zombies.ZombieCommon;
import tp.p3.logic.zombies.ZombieDeportista;
import tp.p3.utils.MyStringUtils;

public class SaveCommand extends Command{

	private String filename;
	
	public SaveCommand() {
		super("save", "[S]ave <filename>", "Save the state of the game to a file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		/*
			cycle: yy
			sunCoins: ss
			level: ll
			remZombies: zz
			plantList: P1, P2, ..., Pn
			zombieList: Z1, Z2, ..., Zm
			
			symbol:lr:x:y:t
		*/
		
		try(BufferedWriter out = new BufferedWriter(new FileWriter(filename))){
			// borrar todo lo que hubiera de antes
			out.flush();
			out.write("Plants Vs Zombies v3.0" + '\n');
			out.write('\n');
			out.write("cycle: " + game.getCiclos() + '\n');
			out.write("sunCoins: " + game.contadorTotalSuncoins() + '\n');
			out.write("level: " + game.getLevel() + '\n');
			out.write("seed: " + game.getSeed() + '\n');
			out.write("remZombies: " + game.zombiesPendientes() + '\n');
			out.write("plantList: ");			
			for(int i = 0; i < game.getFilas(); ++i) {
				for(int j = 0; j < game.getColumnas(); ++j) {
					Plant miplanta = game.getPlantThere(i, j);
					if(miplanta != null) {
						out.write(miplanta.getLetra() + ":" + miplanta.getVida() 
						+ ":" + miplanta.getF() + ":" + miplanta.getC() 
						+ ":" + tiempoSiguienteAccion(miplanta) + " ");
					}
				}
			}
			out.write('\n');
			out.write("zombieList: ");
			for(int i = 0; i < game.getFilas(); ++i) {
				for(int j = 0; j < game.getColumnas(); ++j) {
					Zombie mizombie = game.getZombieThere(i, j);
					if(mizombie != null) {
						out.write(mizombie.getLetra() + ":" + mizombie.getVida() 
						+ ":" + mizombie.getF() + ":" + mizombie.getC() 
						+ ":" + tiempoSiguienteAccion(mizombie) + " ");
					}
				}
			}
			out.write('\n');
			out.write("sunList: ");
			for(int i = 0; i < game.getFilas(); ++i) {
				for(int j = 0; j < game.getColumnas(); ++j) {
					if(game.getSunPosition(i,j) != null) {
						out.write(i + ":" + j + " ");
					}
				}
			}
			out.write('\n');
			out.close();
			System.out.print("Game successfully saved in file <" + this.filename + ">.dat. Use the load command to reload it" + '\n'  + '\n');		
		}
		catch(IOException ioe) {
			throw new CommandExecuteException("Unable to save the game.");
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
			
		if(commandWords.length < 2) {
			if(commandWords[0].equalsIgnoreCase("s") 
					|| commandWords[0].equalsIgnoreCase("save"))
				throw new CommandParseException("Incorrect number of arguments for save command: [S]ave <filename>");
			else return null;
		}
		else {
			if(commandWords[0].equalsIgnoreCase("s") 
					|| commandWords[0].equalsIgnoreCase("save")) {
				if(commandWords.length > 2) {
					throw new CommandParseException("Invalid filename: the filename contains spaces.");
				}
				if(MyStringUtils.fileExists(commandWords[1])) {
					File file = new File(commandWords[1]);
					file.delete();
				}	
				this.filename = commandWords[1];
				comando = this;	
			}	
			else return null;
		}		
		return comando;
	}

	private int tiempoSiguienteAccion(GameObject gameobject) {
		int valor = 0;
		switch(gameobject.getName())
		{
			case "sunflower":
				Sunflower sf = (Sunflower) gameobject;
				valor = sf.getGenerador();
				break;
			case "peashooter":
				valor = 0;
				break;
			case "cherrybomb":
				Cherrybomb cb = (Cherrybomb) gameobject;
				valor = cb.getCuentaAtras();
				break;
			case "wallnut":
				valor = 0;
				break;
			case "zombiecommon":
				ZombieCommon zc = (ZombieCommon) gameobject;
				valor = zc.getMovimiento();
				break;
			case "zombiecaracubo":
				ZombieCaracubo zcc = (ZombieCaracubo) gameobject;
				valor = zcc.getMovimiento();
				break;
			case "zombiedeportista":
				ZombieDeportista zdp = (ZombieDeportista) gameobject;
				valor = zdp.getMovimiento();
				break;
		}	
		return valor;
	}
}
