package tp.p1.control;

import java.util.Scanner;

import tp.p1.logica.Game;
import tp.p1.logica.Sunflower;
import tp.p1.logica.Peashooter;
import tp.p1.logica.Level;

public class Controller {
	
	private Game game;
	private Scanner in;
	
	private Sunflower sf;
	private Peashooter ps;
	
	private int filas = 4;
	private int columnas = 8;
	private int ciclos = 0;
	private String [][] arraytablero = new String[this.filas][this.columnas];
	
	public Controller(Level level, int channel) {
		this.in = new Scanner(System.in);
		this.game = new Game(this.filas, this.columnas, arraytablero, level, channel);
		
	}
	
	public void run() {
		boolean finalizar = false;
		int f,c;
		
		System.out.println(this.game.toString(this.arraytablero));
		
		while(!finalizar) 
		{
			boolean actualizar = true;			
			String[] opcion = this.userCommand();
			/*
			System.out.println(opcion[0]);
			System.out.println(opcion[1]);
			System.out.println(opcion[2]);
			System.out.println(opcion[3]);
			*/
			switch(opcion[0])
			{
				// add
				case "add":
				case "a":
					switch(opcion[1])
					{
						case "sunflower":
						case "s":
							f = Integer.parseInt(opcion[2]);
							c = Integer.parseInt(opcion[3]);
							
							this.game.addSunflower(this.sf = new Sunflower(f,c));
							if(this.game.comprobarSunflower(f, c))
									System.out.println("Ha nacido un Sunflower - OK!");
							break;
						case "peashooter":	
						case "p":
							f = Integer.parseInt(opcion[2]);
							c = Integer.parseInt(opcion[3]);
							
							this.game.addPeashooter(this.ps = new Peashooter(f,c));
							if(this.game.comprobarPeashooter(f, c))
								System.out.println("Ha nacido un Peashooter - OK!");
							break;
						default:
							System.err.println("Planta incorrecta! Use list para más info.");
							actualizar = false;
							break;
					}
				break;
				// reset
				case "reset":
				case "r":
					this.game.inicializarList();
					System.out.println("Partida reseteada!");
					break;
				// list
				case "list":
				case "l":
					this.game.commandList();
					break;
				// help
				case "help":
				case "h":
					this.commandHelp();
					break;
				// exit
				case "exit":
				case "e":
					System.out.println("Game Over");
					finalizar = true;
					break;
				// none
				case "none":
				case "n":
					System.out.println("No se ha realizado ninguna acción.");
					break;
				case "":
					System.out.println("No se ha realizado ninguna acción.");
					break;
				// vacío - pasar turno
				default:
					System.err.println("Comando incorrecto! Use help para más info.");
					actualizar = false;
					break;
			}
			
			if(actualizar)
			{
				this.cyclesUser();				
				this.sunCoinsUser();
				this.remainingZombiesUser();
				// update - girasoles - lanzaguisantes - zombies
				this.game.aumentarContadorSuncoins();
				this.game.updatePeashooter();
				this.game.updateZombie();
				this.game.addZombie();
				
				System.out.println(this.game.toString(this.arraytablero));
				
				if(this.hayGanadorHumano()) {
					System.out.println("Felicidades. Has sido el ganador!");
					finalizar = true;
				}
				else if(this.hayGanadorZombie()) {
					System.out.println("Has perdido! Ánimo para la próxima vez!");
					finalizar = true;
				}
			}
		}
	}
	
	public String[] userCommand() {
		String[] command;
		String tecleado, aux;
		System.out.println("Command > ");
		tecleado = in.nextLine();
		aux = tecleado.toLowerCase();
		command = aux.split(" ");
		/*
		System.out.println("Teclas : " + tecleado);
		System.out.println(command[0]);
		System.out.println(command[1]);
		System.out.println(command[2]);
		System.out.println(command[3]);
		*/
		return command;
	}
	
	public void cyclesUser() {
		System.out.print("Number of cycles: " + this.ciclos + "\n");
		this.ciclos++;
	}
	
	public void sunCoinsUser() {
		System.out.println("Sun coins: " + this.game.contadorTotalSuncoins());
	}
	
	public void remainingZombiesUser() {
		System.out.println("Remaining zombies: " + this.game.zombiesPendientes());
	}
	
	public void commandHelp() {
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
		System.out.println("List: Prints the list of available plants.");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle.");
		
	}
	
	public boolean hayGanadorHumano() {
		return this.game.hayVictoriaHumana();
	}
	public boolean hayGanadorZombie() {
		return this.game.hayVictoriaZombie();
	}
}

