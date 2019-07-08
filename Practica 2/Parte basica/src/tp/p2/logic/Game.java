package tp.p2.logic;
import tp.p2.control.factory.PlantFactory;
import tp.p2.control.factory.ZombieFactory;
import tp.p2.logic.plants.*;
import tp.p2.logic.zombies.*;
public class Game {

	private int filas;
	private int columnas;
	
	private int semilla;
	private Level level;
	
	private SuncoinManager suncoinmanager;
	private ZombieManager zombiemanager;
	private GameObjectList gameobjectlist;

	public Game(int f, int c, Level level, int seed) {
		this.filas = f;
		this.columnas = c;
		this.semilla = seed;
		this.level = level;
		this.suncoinmanager = new SuncoinManager();
		this.zombiemanager = new ZombieManager(level);
		this.gameobjectlist = new GameObjectList(f,c);
		inicializarList();
	}

	public void inicializarList() {
		this.gameobjectlist.resetList();
		this.suncoinmanager.resetSuncoin();
		this.zombiemanager.resetZombieManager(this.level);
	}
	////////////////////////////////////////////////////////////
	//////////////////// *** SUNFLOWER *** /////////////////////
	////////////////////////////////////////////////////////////

	public int contadorTotalSuncoins() {
		return this.suncoinmanager.getSuncoin();
	}

	public void aumentarContadorSuncoins() {
		for(int i = 0;i < this.gameobjectlist.getSoles(); ++i) {
			this.suncoinmanager.add10Suncoins();
		}
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
	
	public int zombiesPendientes() {
		return this.zombiemanager.getNumZ();
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
	
	////////////////////////////////////////////////////////////
	////////////////////// *** PLANTAS PR2 *** /////////////////
	////////////////////////////////////////////////////////////

	public void addPlantToGame(Plant plant, int f, int c) {
		if(!this.gameobjectlist.hayPlant(f, c) && !this.gameobjectlist.hayZombie(f, c))
		{
			if(this.suncoinmanager.subNSuncoin(plant.getCoste()))
			{
				switch(plant.getName())
				{
				case "sunflower":
					this.gameobjectlist.addSunflowerToList(f, c);;
					break;
				case "peashooter":
					this.gameobjectlist.addPeashooterToList(f, c);
					break;
				case "cherrybomb":
					this.gameobjectlist.addCherrybombToList(f, c);
					break;
				case "wallnut":
					this.gameobjectlist.addWallnutToList(f, c);
					break;
				}
			}
			else System.err.println("We need more suns to do that - try again!");			
		}
		else System.err.println("Wrong position - try again!");
	}

	
	public String toStringGameRelease(int f, int c) {
		return this.gameobjectlist.toStringRelease(f, c);
	}
	
	public String toStringGameDebug(int f, int c) {
		return this.gameobjectlist.toStringDebug(f, c);
	}
	
	////////////////////////////////////////////////////////////
	////////////////////// *** ZOMBIES PR2 *** /////////////////
	////////////////////////////////////////////////////////////
	
	public void addZombieToGame(Zombie zombie, int f, int c) {
		if(!this.gameobjectlist.hayPlant(f, c) && !this.gameobjectlist.hayZombie(f, c))
		{
			switch(zombie.getName())
			{
			case "zombiecommon":
				this.gameobjectlist.addZombie(0, f, c);
				break;
			case "zombiecaracubo":
				this.gameobjectlist.addZombie(1, f, c);
				break;
			case "zombiedeportista":
				this.gameobjectlist.addZombie(2, f, c);
				break;
			}			
		}
		else System.err.println("Wrong position - try again!");
	}
}

