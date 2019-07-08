package tp.p3.control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.GameObjectList;
import tp.p3.logic.plants.Cherrybomb;
import tp.p3.logic.plants.Peashooter;
import tp.p3.logic.plants.Sunflower;
import tp.p3.logic.plants.Wallnut;
import tp.p3.logic.zombies.ZombieCaracubo;
import tp.p3.logic.zombies.ZombieCommon;
import tp.p3.logic.zombies.ZombieDeportista;
import tp.p3.utils.MyStringUtils;

public class LoadCommand extends Command{

	private String filename;
	
	public LoadCommand() {
		super("load", "[L]oad <filename>", "Load a previous game file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		GameObjectList golist = new GameObjectList(game.getFilas(),game.getColumnas(),game);
		game.inicializarList();
		
		try(BufferedReader in = new BufferedReader(new FileReader(filename))){
			Object[] flujo = in.lines().toArray();
			String cadena = (String) flujo[0];
			if("Plants Vs Zombies v3.0".matches(cadena))
			{
				/////////////////////////////////////////////////////
				/////////////////////////////// Ciclos
				////////////////////////////////////////////////////
				String[] nuevaCadena =((String) flujo[2]).split(" ");					
				if( !"cycle:".matches(nuevaCadena[0]) || nuevaCadena.length != 2)
					throw new CommandExecuteException("Unable to load the game. Error in cycles.");
				try {
					game.setCiclos(Integer.parseInt(nuevaCadena[1]));
				}
				catch(Exception e) {
					throw new CommandExecuteException("Unable to load the game. Not a number");
				}
				
				/////////////////////////////////////////////////////
				/////////////////////////////// Suncoins
				////////////////////////////////////////////////////
				nuevaCadena =((String) flujo[3]).split(" ");
				if( !"sunCoins:".matches(nuevaCadena[0]) || nuevaCadena.length != 2)
					throw new CommandExecuteException("Unable to load the game. Error in sun count.");	
				try {
					game.setContadorTotalSuncoins(Integer.parseInt(nuevaCadena[1]));
				}
				catch(Exception e) {
					throw new CommandExecuteException("Unable to load the game. Not a number");
				}
				
				/////////////////////////////////////////////////////
				/////////////////////////////// Level
				////////////////////////////////////////////////////
				nuevaCadena =((String) flujo[4]).split(" ");
				if( !"level:".matches(nuevaCadena[0]) || nuevaCadena.length != 2)
					throw new CommandExecuteException("Unable to load the game. Error in level.");	
				game.setLevel(nuevaCadena[1]);
			
				/////////////////////////////////////////////////////
				/////////////////////////////// Seed
				////////////////////////////////////////////////////
				nuevaCadena =((String) flujo[5]).split(" ");
				if( !"seed:".matches(nuevaCadena[0]) || nuevaCadena.length != 2)
					throw new CommandExecuteException("Unable to load the game. Error in seed.");	
				try {
					game.setSeed(Integer.parseInt(nuevaCadena[1]));
				}
				catch(Exception e) {
					throw new CommandExecuteException("Unable to load the game. Not a number");
				}
				
				/////////////////////////////////////////////////////
				/////////////////////////////// remZombies
				////////////////////////////////////////////////////
				nuevaCadena =((String) flujo[6]).split(" ");
				if( !"remZombies:".matches(nuevaCadena[0]) || nuevaCadena.length != 2)
					throw new CommandExecuteException("Unable to load the game. Error in remaining zombies.");	
				try {
					game.setZombiesPendientes(Integer.parseInt(nuevaCadena[1]));
				}
				catch(Exception e) {
					throw new CommandExecuteException("Unable to load the game. Not a number");
				}
				
				/////////////////////////////////////////////////////
				/////////////////////////////// plantList
				////////////////////////////////////////////////////
				nuevaCadena =((String) flujo[7]).split(" ");
				if( !"plantList:".matches(nuevaCadena[0]))
					throw new CommandExecuteException("Unable to load the game. Error in plant list.");	
				String[] nuevaPlanta;
				for(int i = 1; i < nuevaCadena.length; ++i) {
					nuevaPlanta = nuevaCadena[i].split(":");
					if(nuevaPlanta.length != 5)
						throw new CommandExecuteException("Unable to load the game. Error in the" + i + "º in plant list.");	
					else {
						switch(nuevaPlanta[0].toLowerCase()) {
							case "s":
								Sunflower sf = new Sunflower();
								try {
									sf.setVida(Integer.parseInt(nuevaPlanta[1]));
									sf.setF(Integer.parseInt(nuevaPlanta[2]));
									sf.setC(Integer.parseInt(nuevaPlanta[3]));
									sf.setGenerador(Integer.parseInt(nuevaPlanta[4]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in sunflower " + i);
								}
								golist.addSunflowerToListSpecial(sf);
								break;
							case "p":
								Peashooter ps = new Peashooter();
								try {
									ps.setVida(Integer.parseInt(nuevaPlanta[1]));
									ps.setF(Integer.parseInt(nuevaPlanta[2]));
									ps.setC(Integer.parseInt(nuevaPlanta[3]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in peashooter " + i);
								}
								golist.addPeashooterToListSpecial(ps);
								break;
							case "n":
								Wallnut wn = new Wallnut();
								try {
									wn.setVida(Integer.parseInt(nuevaPlanta[1]));
									wn.setF(Integer.parseInt(nuevaPlanta[2]));
									wn.setC(Integer.parseInt(nuevaPlanta[3]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in wallnut " + i);
								}
								golist.addWallnutToListSpecial(wn);
								break;
							case "c":
								Cherrybomb cb = new Cherrybomb();
								try {
									cb.setVida(Integer.parseInt(nuevaPlanta[1]));
									cb.setF(Integer.parseInt(nuevaPlanta[2]));
									cb.setC(Integer.parseInt(nuevaPlanta[3]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in cherrybomb" + i);
								}
								golist.addCherrybombToListSpecial(cb);
								break;
							default:
								throw new CommandExecuteException("Unable to load the game. Not a correct plant in position: " + i);
						}
					}
				}
				/////////////////////////////////////////////////////
				/////////////////////////////// zombieList
				////////////////////////////////////////////////////
				
				nuevaCadena =((String) flujo[8]).split(" ");
				if( !"zombieList:".matches(nuevaCadena[0]))
					throw new CommandExecuteException("Unable to load the game. Error in zombie list.");	
				String[] nuevoZombie;
				for(int i = 1; i < nuevaCadena.length; ++i) {
					nuevoZombie = nuevaCadena[i].split(":");
					if(nuevoZombie.length != 5)
						throw new CommandExecuteException("Unable to load the game. Error in the " + i + "º in zombie list.");	
					else {
						switch(nuevoZombie[0].toLowerCase()) {
							case "z":
								ZombieCommon zcommon = new ZombieCommon();
								try {
									zcommon.setVida(Integer.parseInt(nuevoZombie[1]));
									zcommon.setF(Integer.parseInt(nuevoZombie[2]));
									zcommon.setC(Integer.parseInt(nuevoZombie[3]));
									zcommon.setMovimiento(Integer.parseInt(nuevoZombie[4]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in zombie common " + i);
								}
								golist.addZombieCommonToListSpecial(zcommon);
								break;
							case "w":
								ZombieCaracubo zcaracubo = new ZombieCaracubo();
								try {
									zcaracubo.setVida(Integer.parseInt(nuevoZombie[1]));
									zcaracubo.setF(Integer.parseInt(nuevoZombie[2]));
									zcaracubo.setC(Integer.parseInt(nuevoZombie[3]));
									zcaracubo.setMovimiento(Integer.parseInt(nuevoZombie[4]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in zombie caracubo " + i);
								}
								golist.addZombieCaracuboToListSpecial(zcaracubo);
								break;
							case "x":
								ZombieDeportista zdeportista = new ZombieDeportista();
								try {
									zdeportista.setVida(Integer.parseInt(nuevoZombie[1]));
									zdeportista.setF(Integer.parseInt(nuevoZombie[2]));
									zdeportista.setC(Integer.parseInt(nuevoZombie[3]));
									zdeportista.setMovimiento(Integer.parseInt(nuevoZombie[4]));
								}
								catch(Exception e) {
									throw new CommandExecuteException("Unable to load the game. Error in zombie deportista " + i);
								}
								golist.addZombieDeportistaToListSpecial(zdeportista);
								break;
							default:
								throw new CommandExecuteException("Unable to load the game. Not a correct zombie in position: " + i);	
						}
					}
				}
				
				/////////////////////////////////////////////////////
				/////////////////////////////// sunList
				////////////////////////////////////////////////////
				
				nuevaCadena =((String) flujo[9]).split(" ");
				if( !"sunList:".matches(nuevaCadena[0]))
					throw new CommandExecuteException("Unable to load the game. Error in sun list.");	
				String[] nuevoSun;
				for(int i = 1; i < nuevaCadena.length; ++i) {
					nuevoSun = nuevaCadena[i].split(":");
					if(nuevoSun.length != 2)
						throw new CommandExecuteException("Unable to load the game. Error in the " + i + "º in sun list.");	
					else {
						try {
							game.addSun(Integer.parseInt(nuevoSun[0]), Integer.parseInt(nuevoSun[1]));
						}					
						catch(Exception e) {
							throw new CommandExecuteException("Unable to load the game. Error in suncoin " + i);
						}
					}
				}
				
				System.out.print("Game successfully load the file <" + this.filename + ">.dat." + '\n'  + '\n');
				game.setNewGameObjectList(golist);
				game.changePrintMode(true, false);
				game.printBoard();
			}
			else
				throw new CommandExecuteException("Unable to load the game.");
		}
		catch(IOException ioe) {
			throw new CommandExecuteException(ioe.getMessage());
		}		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando = null;
		
		if(commandWords.length < 2) {
			if(commandWords[0].equalsIgnoreCase("l") 
					|| commandWords[0].equalsIgnoreCase("load"))
				throw new CommandParseException("Incorrect number of arguments for load command: [L]oad <filename>");
			else return null;
		}
		else {
			if(commandWords[0].equalsIgnoreCase("l") 
					|| commandWords[0].equalsIgnoreCase("load")) {
				if(commandWords.length > 2) {
					throw new CommandParseException("Invalid filename: the filename contains spaces.");
				}
					if(MyStringUtils.fileExistsCaseSensitive(commandWords[1])) {
						this.filename = confirmFileName(commandWords[1]);
						comando = this;	
					}
					else 
						throw new CommandParseException("Invalid filename: the filename does not exist.");
			}	
			else return null;
		}		
		return comando;
	}
	
	private String confirmFileName(String filename) throws CommandParseException {
		String loadFileName = filename;
		if (MyStringUtils.isValidFilename(loadFileName)) {
			return loadFileName;
		}
		else {
			throw new CommandParseException("Invalid filename: the filename contains invalid characters"); 
		}
	}

}
