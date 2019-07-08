package tp.p2.control;

import java.util.Scanner;

import tp.p2.control.commands.Command;
import tp.p2.control.commands.CommandParser;
import tp.p2.logic.DebugPrinter;
import tp.p2.logic.Game;
import tp.p2.logic.GamePrinter;
import tp.p2.logic.Level;
import tp.p2.logic.ReleasePrinter;

public class Controller {
	
	private Game game;
	private GamePrinter gamePrinter[] = new GamePrinter[2];
	private DebugPrinter dp = new DebugPrinter();
	private ReleasePrinter rp = new ReleasePrinter();
	
	private Scanner in;
	
	private int filas = 4;
	private int columnas = 8;
	private int semilla;
	private Level level;
	
	private boolean exit = false;
	private boolean noprint = false;
	private boolean releaseON = true;
	private boolean update = true;
	
	public Controller(Level level, int seed) {
		this.semilla = seed;
		this.level = level;
		this.in = new Scanner(System.in);
		this.game = new Game(this.filas, this.columnas, level, seed);
		gamePrinter[0] = dp;
		gamePrinter[1] = rp;
		
	}
	
	public void run() {		
		this.seedUser();
		printStateGame();		
		
		while(!exit) 
		{
			printBoard();
			
			String[] opcion = this.userCommand();
			Command command = CommandParser.parseCommand(opcion, this);
			if(command != null)
				command.execute(game, this);			
			else {
				System.err.println("Oh something went wrong! - try again");
				setNoUpdate();
				setNoPrintGame();
			}
			
			if(!exit) {
				printStateGame();
				updateGame();			
				setGanador();	
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
		return command;
	}
	
	public void seedUser() {
		System.out.print("Random seed used: " + this.semilla + "\n");
	}
	
	public void cyclesUser() {
		System.out.print("Number of cycles: " + this.game.getCiclos() + "\n");
	}
	
	public void sunCoinsUser() {
		System.out.println("Sun coins: " + this.game.contadorTotalSuncoins());
	}
	
	public void remainingZombiesUser() {
		System.out.println("Remaining zombies: " + this.game.zombiesPendientes());
	}
	
	public boolean hayGanadorHumano() {
		return this.game.hayVictoriaHumana();
	}
	public boolean hayGanadorZombie() {
		return this.game.hayVictoriaZombie();
	}
	
	public int getF() {
		return this.filas;
	}
	
	public int getC() {
		return this.columnas;
	}
	
	public void printStateGame() {
		this.cyclesUser();				
		this.sunCoinsUser();
		this.remainingZombiesUser();
		if(!this.releaseON) {
			System.out.println("Level: " + level.getLevel());
			System.out.println("Seed: " + this.semilla);
		}
	}
	
	public void updateGame() {	
		if(update) {
			this.game.update();
			//this.game.aumentarContadorSuncoins();	PR2 basica 
			this.game.addZombie();
			this.game.incrementarCiclos();
			this.game.alternarBoolSunTurno(true);
		}
		else
			this.update = true;
	}
	
	public void setNoPrintGame() {
		this.noprint = true;
	}
	
	public void setNoUpdate() {
		this.update = false;
	}
	public void setFinalizar() {
		this.exit = true;
	}
	
	public void printBoard() {
		
		// Modo Release
		if(!noprint && this.releaseON) {
			System.out.println(this.gamePrinter[1].printGame(this.game));
		}
		// Modo Debug
		else if(!noprint && !this.releaseON) {
			System.out.println(this.gamePrinter[0].printGame(this.game));
		}
		else {
			this.noprint = false;
		}
		
	}
	
	public void changePrintMode() {
		if(this.releaseON)
			this.releaseON = false;
		else
			this.releaseON = true;		
	}
	
	public void setGanador() {
		if(this.hayGanadorHumano()) {
			printBoard();
			System.out.println("Player win.");
			setFinalizar();
		}
		else if(this.hayGanadorZombie()) {
			printBoard();
			System.out.println("Zombies win.");
			setFinalizar();
		}	
	}	
}

