package tp.p3.logic;
import tp.p3.control.factory.PlantFactory;
import tp.p3.control.factory.ZombieFactory;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.pasives.Sun;
import tp.p3.logic.plants.*;
import tp.p3.logic.zombies.*;
public class Game {

	private int filas;
	private int columnas;
	
	private int semilla;
	private Level level;
	private int ciclos = 0;
	private boolean cogerSunTurno = true;
	private boolean finalizarjuego = false;
	private boolean release = true;
	private boolean debug = false;
	private boolean update = true;
	
	private GamePrinter gamePrinter[] = new GamePrinter[2];	
	private DebugPrinter dp = new DebugPrinter();
	private ReleasePrinter rp = new ReleasePrinter();
	
	private SunManager sunmanager;
	private ZombieManager zombiemanager;
	private GameObjectList gameobjectlist;

	public Game(int f, int c, Level level, int seed) {
		this.filas = f;
		this.columnas = c;
		this.semilla = seed;
		this.level = level;
		this.sunmanager = new SunManager(f,c);
		this.zombiemanager = new ZombieManager(level);
		this.gameobjectlist = new GameObjectList(f,c,this);
		gamePrinter[0] = dp;
		gamePrinter[1] = rp;
		inicializarList();
	}

	public void inicializarList() {
		this.gameobjectlist.resetList();
		this.sunmanager.resetSuncoin();
		this.zombiemanager.resetZombieManager(this.level);
	}
	////////////////////////////////////////////////////////////
	//////////////////// *** SUNFLOWER *** /////////////////////
	////////////////////////////////////////////////////////////
	
	public Sun getSunPosition(int f, int c) {
		return this.sunmanager.getPositionSun(f, c);
	}
	
	public void addSun(int f, int c) {
		Sun sun = new Sun();
		sun.setF(f);
		sun.setC(c);
		this.sunmanager.addSun(sun);
	}
	
	public void alternarBoolSunTurno(boolean sunturno) {
			this.cogerSunTurno = sunturno;
	}
	
	public boolean getBoolSunTurno() {
		return this.cogerSunTurno;
	}
	
	public boolean catchSun(int f, int c) {
		if(this.sunmanager.catchSun(f, c)) {
			this.cogerSunTurno = false;
			return true;
		}
		else
			System.err.println("Wrong position - no sun found - try again!");
		return false;
	}

	////////////////////////////////////////////////////////////	
	////////////////////// *** ZOMBIE *** //////////////////////
	////////////////////////////////////////////////////////////

	public void addZombie() {
		if(this.zombiemanager.isZombieAdded(this.semilla) 
				&& (this.zombiemanager.getNumZ() > 0)) 
		{
			int aux = this.zombiemanager.whereAddZombie(this.filas);
			
			if(!this.gameobjectlist.hayZombie(aux, this.columnas - 1))
			{
				this.gameobjectlist.addZombie(this.zombiemanager.whichZombie(),aux, this.columnas - 1);
				this.zombiemanager.subNumZ();
			}
		}
	}
	
	////////////////////////////////////////////////////////////	
	//////////////////// *** AUXILIARES *** ////////////////////
	////////////////////////////////////////////////////////////
	
	public int getFilas() {
		return this.filas;
	}
	
	public int getColumnas() {
		return this.columnas;
	}
	
	public boolean hayAlgo(int f, int c) {
		return (this.gameobjectlist.hayPlant(f, c) || this.gameobjectlist.hayZombie(f, c));
	}
	
	public void update() {
		this.gameobjectlist.update();
	}

	public boolean hayVictoriaHumana() {
		if((this.zombiesPendientes() == 0) 
				&& (this.gameobjectlist.getContador() == 0))	
			return true;
		return false;
	}

	public boolean hayVictoriaZombie() {
		for(int i = 0; i < this.filas; ++i)
			if(this.gameobjectlist.hayZombie(i, 0))
				return true;
		return false;
	}

	public void commandList() {
		PlantFactory pf = new PlantFactory();
		pf.listOfAvailablePlants();
	}
	
	public void commandZombieList() {
		ZombieFactory zf = new ZombieFactory();
		zf.listOfAvailableZombies();
	}
	
	public void setGanador() {
		if(this.hayVictoriaHumana()) {			
			System.out.println("Player win.");
			printBoard();
			setFinalizar();
		}
		else if(this.hayVictoriaZombie()) {			
			System.out.println("Zombies win.");
			printBoard();
			setFinalizar();
		}	
	}	
	
	public void setFinalizar() {
		this.finalizarjuego = true;
	}
	
	public boolean getFinalizar() {
		return this.finalizarjuego;
	}
	
	public void printBoard() {
		
		// Modo Release
		if(this.release && !this.debug) {
			System.out.println(this.gamePrinter[1].printGame(this));
		}
		// Modo Debug
		else if(!this.release && this.debug) {
			System.out.println(this.gamePrinter[0].printGame(this));
		}		
	}
	
	public void changePrintMode(boolean release, boolean debug) {
		this.release = release;
		this.debug = debug;
	}
	
	public void updateGame() {	
		if(update) {
			this.update();
			this.addZombie();
			this.incrementarCiclos();
			this.alternarBoolSunTurno(true);
		}
		else
			this.update = true;
	}
	
	public void setNoUpdate() {
		this.update = false;
	}		
	
	public void printStateGame() {
		this.cyclesUser();				
		this.sunCoinsUser();
		this.remainingZombiesUser();
		if(this.debug && !this.release) {
			System.out.println("Level: " + level.getLevel());
			System.out.println("Seed: " + this.semilla);
		}
	}
	
	public void seedUser() {
		System.out.print("Random seed used: " + this.semilla + "\n");
	}
	
	public void cyclesUser() {
		System.out.print("Number of cycles: " + this.getCiclos() + "\n");
	}
	
	public void sunCoinsUser() {
		System.out.println("Sun coins: " + this.contadorTotalSuncoins());
	}
	
	public void remainingZombiesUser() {
		System.out.println("Remaining zombies: " + this.zombiesPendientes());
	}	
	
	public void setLevel(String dato) throws CommandExecuteException {
		Level aux = Level.fromParamL(dato.toLowerCase());
		if (aux == null)
			throw new CommandExecuteException("Wrong level: level must be one of: EASY, HARD, INSANE");
		else
			this.level = aux;			
	}
	
	////////////////////////////////////////////////////////////
	//////////////////////// *** SAVE *** //////////////////////
	////////////////////////////////////////////////////////////
	
	public int getCiclos() {
		return this.ciclos;
	}
	
	public int getSeed() {
		return this.semilla;
	}
	
	public String getLevel() {
		return this.level.getLevel();
	}
	
	public int zombiesPendientes() {
		return this.zombiemanager.getNumZ();
	}
	
	public int contadorTotalSuncoins() {
		return this.sunmanager.getSuncoin();
	}
	
	public Plant getPlantThere(int f, int c) {
		return this.gameobjectlist.getPlantaCasilla(f, c);	
	}
	
	public Zombie getZombieThere(int f, int c) {
		return this.gameobjectlist.getZombieCasilla(f, c);
	}
	
	////////////////////////////////////////////////////////////
	//////////////////////// *** LOAD *** //////////////////////
	////////////////////////////////////////////////////////////
	
	public void setNewGameObjectList(GameObjectList golist) {
		this.gameobjectlist = golist;
	}

	public void setCiclos(int i) {
		this.ciclos = i;
	}
	
	public void setSeed(int numero) {
		this.semilla = numero;
	}
	
	public void setZombiesPendientes(int numero) {
		this.zombiemanager.setNumZ(numero);
	}
	
	public void setContadorTotalSuncoins(int numero) {
		this.sunmanager.setSuncoin(numero);
	}
	
	////////////////////////////////////////////////////////////
	//////////////////////// *** CICLOS *** ////////////////////
	////////////////////////////////////////////////////////////		
	
	public void incrementarCiclos() {
		this.ciclos++;
		// añadir sun aleatorio
		this.sunmanager.update(this);
	}
	
	////////////////////////////////////////////////////////////
	////////////////////// *** PLANTAS PR2 *** /////////////////
	////////////////////////////////////////////////////////////

	public boolean addPlantToGame(Plant plant, int f, int c) throws CommandExecuteException {
		boolean correcto = false;
		if(!this.gameobjectlist.hayPlant(f, c) && !this.gameobjectlist.hayZombie(f, c))
		{
			if(this.sunmanager.subNSuncoin(plant.getCoste()))
			{
				switch(plant.getName())
				{
				case "sunflower":
					this.gameobjectlist.addSunflowerToList(f, c);
					correcto = true;
					break;
				case "peashooter":
					this.gameobjectlist.addPeashooterToList(f, c);
					correcto = true;
					break;
				case "cherrybomb":
					this.gameobjectlist.addCherrybombToList(f, c);
					correcto = true;
					break;
				case "wallnut":
					this.gameobjectlist.addWallnutToList(f, c);
					correcto = true;
					break;
				}
			}
			else 
				throw new CommandExecuteException("Failed to add " + plant.getName() + ": not enough suncoins to buy it");			
		}
		else 
			throw new CommandExecuteException("Failed to add " + plant.getName() + ": position (" + f + ", " + c + ") is already occupied");
		
		return correcto;
	}

	
	public String toStringGameRelease(int f, int c) {
		return this.gameobjectlist.toStringRelease(f, c);
	}
	
	public String toStringGameDebug(int f, int c) {
		return this.gameobjectlist.toStringDebug(f, c);
	}
	
	public String toStringGameDebugSuns(int f, int c) {
		return this.gameobjectlist.toStringDebugSuns(f, c);
	}
	////////////////////////////////////////////////////////////
	////////////////////// *** ZOMBIES PR2 *** /////////////////
	////////////////////////////////////////////////////////////
	
	public boolean addZombieToGame(Zombie zombie, int f, int c) {
		boolean correcto = false;
		if(!this.gameobjectlist.hayPlant(f, c) && !this.gameobjectlist.hayZombie(f, c))
		{
			switch(zombie.getName())
			{
			case "zombiecommon":
				this.gameobjectlist.addZombie(0, f, c);
				correcto = true;
				break;
			case "zombiecaracubo":
				this.gameobjectlist.addZombie(1, f, c);
				correcto = true;
				break;
			case "zombiedeportista":
				this.gameobjectlist.addZombie(2, f, c);
				correcto = true;
				break;
			}	
			this.zombiemanager.subNumZ();
		}
		else System.err.println("Wrong position - try again!");
		
		return correcto;
	}
}

