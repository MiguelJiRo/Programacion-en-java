package tp.p3.control;

import java.util.Scanner;

import tp.p3.control.commands.Command;
import tp.p3.control.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.Level;

public class Controller {
	
	private Game game;	
	private Scanner in;
	
	private int filas = 4;
	private int columnas = 8;
	private int semilla;
	
	public Controller(Level level, int seed) {
		this.in = new Scanner(System.in);
		this.game = new Game(this.filas, this.columnas, level, seed);	
		this.semilla = seed;
	}
	
	public void run() {		
		System.out.print("Welcome to plantsVsZombies v3.0" + '\n');
		System.out.print("Random seed used: " + semilla + '\n');
		System.out.print('\n');
		while(!game.getFinalizar()) 
		{			
			printStateGame();
			
			System.out.print("Command > ");
			String[]  words = in.nextLine().trim().split("\\s+");
			
			try	{
				Command command = CommandParser.parseCommand(words);
					if(command != null) {
							if(command.execute(game)) {
								this.game.printBoard();
								updateGame();
							}
					}
					else
						System.err.println("Unknown command - try again!");						
					
			}
			catch(CommandParseException | CommandExecuteException ex) {
				System.err.format(ex.getMessage() +	"%n%n");
			}	
			
			game.setGanador();
		}
	}
	
	public void updateGame() {
		game.updateGame();
	}
	
	public void printStateGame() {
		game.printStateGame();
	}
}

