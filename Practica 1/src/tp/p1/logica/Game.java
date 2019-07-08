package tp.p1.logica;

public class Game {
	
	private int filas;
	private int columnas;
	private int max = 30;
	private int channel;
	
	private GamePrinter gameprinter;
	private SunflowerList sunflowerlist;
	private PeashooterList peashooterlist;
	private ZombieList zombielist;
	private SuncoinManager suncoinmanager;
	private ZombieManager zombiemanager;
	
	private String [][] arraytablero;
	
	public Game(int f, int c, String arraytablero[][], Level level, int channel) {
		this.filas = f;
		this.columnas = c;
		this.channel = channel;
		this.arraytablero = arraytablero;
		this.gameprinter = new GamePrinter(this.filas,this.columnas,this);
		this.sunflowerlist = new SunflowerList(this.max);
		this.peashooterlist = new PeashooterList(this.max);
		this.zombielist = new ZombieList(level.getNumZombies());
		this.suncoinmanager = new SuncoinManager();
		this.zombiemanager = new ZombieManager(level);
		inicializarList();
	}
	
	public void inicializarList() {
		this.sunflowerlist.resetSunflower();
		this.peashooterlist.resetPeashooter();
		this.zombielist.resetZombie();
		this.suncoinmanager.resetSuncoin();
	}
	////////////////////////////////////////////////////////////
	//////////////////// *** SUNFLOWER *** /////////////////////
	////////////////////////////////////////////////////////////
	
	public void addSunflower(Sunflower sf) {
		if(this.hayAlgoAhi(sf.getF(), sf.getC()))
		{
			if(this.suncoinmanager.subNSuncoin(sf.getCoste()))
			{
				this.sunflowerlist.addSunflower(sf.getF(), sf.getC());
				this.arraytablero[sf.getF()][sf.getC()] = this.sunflowerlist.toString(sf.getF(), sf.getC());
			}
			else 
				System.err.println("We need more suns to do that - try again!");
		}
		else
			System.err.println("Wrong position - try again!");
	}
	
	public boolean comprobarSunflower(int f, int c) {
		return this.sunflowerlist.haySunflower(f, c);
	}
	
	public int contadorTotalSuncoins() {
		return this.suncoinmanager.getSuncoin();
	}
	
	public void aumentarContadorSuncoins() {
		for(int i = 0;i < this.sunflowerlist.getContador(); ++i) {
			this.suncoinmanager.add10Suncoins();
		}
	}
	////////////////////////////////////////////////////////////
	//////////////////// *** PEASHOOTER *** ////////////////////
	////////////////////////////////////////////////////////////
	
	public void addPeashooter(Peashooter ps) {
		if(this.hayAlgoAhi(ps.getF(), ps.getC()))
		{
			if(this.suncoinmanager.subNSuncoin(ps.getCoste()))
			{
				this.peashooterlist.addPeashooter(ps.getF(), ps.getC());
				this.arraytablero[ps.getF()][ps.getC()] = this.peashooterlist.toString(ps.getF(), ps.getC());
			}
			else 
				System.err.println("We need more suns to do that - try again!");
		}
		else
			System.err.println("Wrong position - try again!");
	}
	
	public boolean comprobarPeashooter(int f, int c) {
		return this.peashooterlist.hayPeashooter(f, c);
	}
	
	public void updatePeashooter() {
		// lanzar guisantes
		boolean encontrado = false;
		for(int i = 0; i < this.filas; ++i) {
			for(int j = 0; j < this.columnas; ++j) {
				if(this.peashooterlist.hayPeashooter(i, j)) {
					for(int k = j; k < this.columnas && !encontrado; ++k) {
						if(this.zombielist.hayZombie(i, k)) {
							this.zombielist.tocado(i, k);
							this.arraytablero[i][k] = this.zombielist.toString(i,k);
							encontrado = true;
						}
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////	
	////////////////////// *** ZOMBIE *** //////////////////////
	////////////////////////////////////////////////////////////
	
	
	public int zombiesPendientes() {
		return this.zombiemanager.getNumZ();
	}
	
	public void addZombie() {
		if(this.zombiemanager.isZombieAdded() 
				&& (this.zombiemanager.getNumZ() > 0)) 
		{
			int aux;
			if(this.channel == 0)
				aux = 0;
			else
				aux = this.zombiemanager.whereAddZombie(this.channel);
			
			if(!this.zombielist.hayZombie(aux, this.columnas - 1))
			{
				this.zombielist.addZombie(aux, this.columnas - 1);
				this.zombiemanager.subNumZ();
				this.arraytablero[aux][this.columnas - 1] = this.zombielist.toString(aux, this.columnas - 1);
			}
		}
	}
	
	public void updateZombie() 
	{
		for(int i = 0; i < this.filas; ++i) {
			for(int j = 0; j < this.columnas; ++j) {
				if(this.zombielist.hayZombie(i, j)) 
				{
					// los zombies avanzan
					if((j-1) >= 0 
							&& !this.sunflowerlist.haySunflower(i, j-1) 
							&& !this.peashooterlist.hayPeashooter(i, j-1)
							&& !this.zombielist.hayZombie(i, j-1))
					{
						if(this.zombielist.moverZombie(i, j))
						{
							String aux = this.arraytablero[i][j];
							this.arraytablero[i][j-1] = aux;
							this.arraytablero[i][j] = null;
						}
					}
					// los zombies atacan a los Peashooters
					else if((j-1) >= 0 
							&& this.peashooterlist.hayPeashooter(i, j-1)) 
					{
						this.peashooterlist.tocado(i,j-1);
						this.arraytablero[i][j-1] = this.peashooterlist.toString(i,j-1);
					}
					// los zombies atacan a los Sunflowers
					else if((j-1) >= 0 
							&& this.sunflowerlist.haySunflower(i, j-1)) 
					{
						this.sunflowerlist.tocado(i,j-1);
						// solo para cuando vida sunflower > 1
						//this.arraytablero[i][j-1] = this.sunflowerlist.toString(i,j-1);
					}
				}
			}
		}
			
	}
	
	public boolean comprobarZombie(int f, int c){
		return this.zombielist.hayZombie(f, c);
	}
	
	////////////////////////////////////////////////////////////	
	//////////////////// *** AUXILIARES *** ////////////////////
	////////////////////////////////////////////////////////////
	
	public boolean hayVictoriaHumana() {
		if((this.zombiesPendientes() == 0) 
				&& (this.zombielist.getContador() == 0))	
			return true;
		return false;
	}
	
	public boolean hayVictoriaZombie() {
		for(int i = 0; i < this.filas; ++i)
			if(this.zombielist.hayZombie(i, 0))
				return true;
		return false;
	}
	
	public boolean hayAlgoAhi(int f, int c) {
		return !(this.sunflowerlist.haySunflower(f, c) || this.peashooterlist.hayPeashooter(f, c));
	}
	
	public void commandList() {
		System.out.println("[S]unflower: Cost 20 suncoins Harm: 0");
		System.out.println("[P]eashooter: Cost 50 suncoins Harm: 1");
	}
	
	/**
	 * Metodo que dibuja el tablero de juego.
	 * @return String con la información del tablero del juego.
	 */	
	public String toString(String v[][]) 
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.gameprinter.toString(v));
		return builder.toString();
	}
}

