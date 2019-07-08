package tp.p3.logic;

import tp.p3.logic.pasives.Sun;
import tp.p3.logic.plants.*;
import tp.p3.logic.zombies.*;

public class GameObjectList {

	private int max = 3;
	private int posicionNuevo = 0;
	private int contadorSunflower = 0;
	private int contadorZombiesVivos = 0;
	private GameObject[] mapaPlantZombie;
	private int contadorSoles;
	private Game game;
	
	private int columna;
	private int fila;
	
	public GameObjectList(int f,int c, Game game) {
		this.mapaPlantZombie = new GameObject[this.max];
		this.fila = f;
		this.columna = c;
		this.game = game;
	}
	
	public void resetList() {
		for(int i = 0; i < this.max;++i)
			this.mapaPlantZombie[i] = null;
		this.posicionNuevo = 0;
		this.contadorSunflower = 0;
	}
	
	////////////////////////////////////////////////////////////
	//////////////////// *** ADD PLANTS*** /////////////////////
	////////////////////////////////////////////////////////////
	
	public void addSunflowerToList(int f, int c) {
		Sunflower sunflower = new Sunflower();
		sunflower.setF(f);
		sunflower.setC(c);
		sunflower.iniGenerador();
		this.mapaPlantZombie[this.posicionNuevo] = sunflower;
		this.posicionNuevo++;
		this.contadorSunflower++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addSunflowerToListSpecial(Sunflower sf) {
		this.mapaPlantZombie[this.posicionNuevo] = sf;
		this.posicionNuevo++;
		this.contadorSunflower++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addPeashooterToList(int f, int c) {
		Peashooter peashooter = new Peashooter();
		peashooter.setF(f);
		peashooter.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = peashooter;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addPeashooterToListSpecial(Peashooter ps) {
		this.mapaPlantZombie[this.posicionNuevo] = ps;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addCherrybombToList(int f, int c) {
		Cherrybomb cherrybomb = new Cherrybomb();
		cherrybomb.setF(f);
		cherrybomb.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = cherrybomb;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addCherrybombToListSpecial(Cherrybomb cb) {
		this.mapaPlantZombie[this.posicionNuevo] = cb;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addWallnutToList(int f, int c) {
		Wallnut wallnut = new Wallnut();
		wallnut.setF(f);
		wallnut.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = wallnut;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addWallnutToListSpecial(Wallnut wn) {
		this.mapaPlantZombie[this.posicionNuevo] = wn;
		this.posicionNuevo++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	////////////////////////////////////////////////////////////
	//////////////////// *** ADD ZOMBIE*** /////////////////////
	////////////////////////////////////////////////////////////
	
	public void addZombie(int elegido,int f, int c) {
		switch(elegido) {
			case 0:
				this.addZombieCommonToList(f, c);
				break;
			case 1:
				this.addZombieCaracuboToList(f, c);
				break;
			case 2:
				this.addZombieDeportistaToList(f, c);
				break;
		}
	}
	
	public void addZombieCommonToList(int f, int c) {		
		ZombieCommon zombiecommon = new ZombieCommon();
		zombiecommon.setF(f);
		zombiecommon.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = zombiecommon;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addZombieCommonToListSpecial(ZombieCommon zc) {		
		this.mapaPlantZombie[this.posicionNuevo] = zc;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addZombieDeportistaToList(int f, int c) {		
		ZombieDeportista zombiedeportista = new ZombieDeportista();
		zombiedeportista.setF(f);
		zombiedeportista.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = zombiedeportista;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addZombieDeportistaToListSpecial(ZombieDeportista zd) {		
		this.mapaPlantZombie[this.posicionNuevo] = zd;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addZombieCaracuboToList(int f, int c) {		
		ZombieCaracubo zombiecaracubo = new ZombieCaracubo();
		zombiecaracubo.setF(f);
		zombiecaracubo.setC(c);
		this.mapaPlantZombie[this.posicionNuevo] = zombiecaracubo;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	public void addZombieCaracuboToListSpecial(ZombieCaracubo zcubo) {		
		this.mapaPlantZombie[this.posicionNuevo] = zcubo;
		this.posicionNuevo++;
		this.contadorZombiesVivos++;
		// Redimensionar en caso de haber alcanzado el maximo
		if(this.max == this.posicionNuevo)
			redimensionarMapa();
	}
	
	////////////////////////////////////////////////////////////
	////////////////////// *** REMOVE *** //////////////////////
	////////////////////////////////////////////////////////////
	
	public void remove(int i) {		
		if(this.mapaPlantZombie[i] instanceof Sunflower)
			this.contadorSunflower--;
		else if(this.mapaPlantZombie[i] instanceof Zombie)
			contadorZombiesVivos--;
		this.mapaPlantZombie[i] = null;
	}
	
	////////////////////////////////////////////////////////////
	////////////////////// *** HAY ALGO*** /////////////////////
	////////////////////////////////////////////////////////////
	
	public boolean hayPlant(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null
					&& this.mapaPlantZombie[i] instanceof Plant
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				return true;
			}							
		}	
		return false;
	}
	
	public boolean hayZombie(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null
					&& this.mapaPlantZombie[i] instanceof Zombie
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				return true;
			}							
		}	
		return false;
	}
	
	////////////////////////////////////////////////////////////
	//////////////////// *** ESPECIALES*** /////////////////////
	////////////////////////////////////////////////////////////
	
	public void redimensionarMapa() {
		GameObject[] nuevoMapa = new GameObject[this.max*2];
		for(int i = 0; i < this.posicionNuevo; ++i)
			nuevoMapa[i] = this.mapaPlantZombie[i];
		this.max *= 2;
		this.mapaPlantZombie = nuevoMapa;
	}
	
	public Plant getPlantaCasilla(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null
					&& this.mapaPlantZombie[i] instanceof Plant
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				return (Plant) this.mapaPlantZombie[i];
			}							
		}
		return null;
	}
	
	public Zombie getZombieCasilla(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null
					&& this.mapaPlantZombie[i] instanceof Zombie
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				return (Zombie) this.mapaPlantZombie[i];
			}							
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////
	/////////////////////// *** TOCADO*** //////////////////////
	////////////////////////////////////////////////////////////
	
	public void tocado(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null 
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				if(this.mapaPlantZombie[i].getVida() > 1)
				{
					this.mapaPlantZombie[i].setVida(this.mapaPlantZombie[i].getVida() - 1);
				}
				else
					this.remove(i);
			}							
		}
	}
	
	////////////////////////////////////////////////////////////
	///////////////////// *** MOVIMIENTO *** ///////////////////
	////////////////////////////////////////////////////////////
	
	private void moverZombie(int i) {		
		if(this.mapaPlantZombie[i] instanceof ZombieCommon) 
			moverZombieCommon(i);
		else if(this.mapaPlantZombie[i] instanceof ZombieCaracubo)
			moverZombieCaracubo(i);
		else if(this.mapaPlantZombie[i] instanceof ZombieDeportista)
			moverZombieDeportista(i);	
	}
	
	private void moverZombieCommon(int i) {
		if(this.mapaPlantZombie[i].getMovimiento() <= 0
				&& !this.hayPlant(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1)
				&& !this.hayZombie(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1))
		{
			this.mapaPlantZombie[i].setMovimiento(1);
			this.mapaPlantZombie[i].setC(this.mapaPlantZombie[i].getC() - 1);
		}
		else {
			this.mapaPlantZombie[i].setMovimiento(this.mapaPlantZombie[i].getMovimiento()-1);
		}
	}
	
	private void moverZombieCaracubo(int i) {
		if(this.mapaPlantZombie[i].getMovimiento() <= 0
				&& !this.hayPlant(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1)
				&& !this.hayZombie(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1))
		{
			this.mapaPlantZombie[i].setMovimiento(3);
			this.mapaPlantZombie[i].setC(this.mapaPlantZombie[i].getC() - 1);
		}
		else
			this.mapaPlantZombie[i].setMovimiento(this.mapaPlantZombie[i].getMovimiento()-1);
	}
	
	private void moverZombieDeportista(int i) {
		if(!this.hayPlant(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1)
				&& !this.hayZombie(this.mapaPlantZombie[i].getF(), this.mapaPlantZombie[i].getC()-1))
			this.mapaPlantZombie[i].setC(this.mapaPlantZombie[i].getC() - 1);
	}
	
	////////////////////////////////////////////////////////////
	///////////////////// *** CONTADORES *** ///////////////////
	////////////////////////////////////////////////////////////

	public int getContador() {
		return this.contadorZombiesVivos;
	}

	public int getSoles() {
		return this.contadorSoles;
	}
	
	////////////////////////////////////////////////////////////
	/////////////////////// *** UPDATE*** //////////////////////
	////////////////////////////////////////////////////////////
	
	public void update() {
		this.contadorSoles = 0;
		for(int i = 0; i < this.posicionNuevo; ++i) {
			if(this.mapaPlantZombie[i] != null) 
			{
				switch(this.mapaPlantZombie[i].getName())
				{
					case "sunflower":
						updateSunflower((Sunflower) this.mapaPlantZombie[i]);
						break;
					case "peashooter":
						updatePeashooter(i);
						break;
					case "cherrybomb":
						if(updateCherrybomb((Cherrybomb) this.mapaPlantZombie[i], i))
							this.mapaPlantZombie[i] = null;
						break;
					case "wallnut":
						//sin update - wall unit
						break;
					case "zombiecommon":
					case "zombiecaracubo":
					case "zombiedeportista":
						moverZombie(i);
						updateZombie(i);
						break;
				}	
			}
		}
	}
	
	private void updateZombie(int i) 
	{
		for(int j = 0; j < this.posicionNuevo; ++j) 
		{
			if(this.mapaPlantZombie[j] != null
					&& this.mapaPlantZombie[j] instanceof Plant
					&& this.mapaPlantZombie[j].getF() == this.mapaPlantZombie[i].getF()
					&& this.mapaPlantZombie[j].getC() == this.mapaPlantZombie[i].getC() - 1) 
			{
				tocado(this.mapaPlantZombie[j].getF(),this.mapaPlantZombie[j].getC());
			}
			
		}
	}
	
	private boolean updateCherrybomb(Cherrybomb cherrybomb, int k) {
		if(cherrybomb.getCuentaAtras() == 0) {
			int f = this.mapaPlantZombie[k].getF();
			int c = this.mapaPlantZombie[k].getC();
			for(int i = f - 1; i <= f + 1; ++i) {
				for(int j = c - 1; j <= c + 1; ++j) {
					if(i >= 0 && i < this.fila
							&& j >= 0 && j < this.columna
							&& this.hayZombie(i, j)) 
					{
						int objetivo = encontrarPosicion(i,j);
						if(objetivo >= 0)
							this.remove(objetivo);
					}
				}
			}
			return true;
		}
		else {
			cherrybomb.subCuentaAtras();
			return false;
		}
	}
	
	private int encontrarPosicion(int f,int c) {
		int i = 0;
		while( i < this.posicionNuevo ) 
		{
			if(this.mapaPlantZombie[i] != null
					&& this.mapaPlantZombie[i] instanceof Zombie
					&& this.mapaPlantZombie[i].getF() == f
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				return i;
			}	
			++i;
		}		
		return -1;				
	}
	
	private void updatePeashooter(int i) {
		boolean encontrado = false;
		// lanzar guisantes
		int j = this.mapaPlantZombie[i].getC();
		while( j < this.columna && !encontrado) 
		{
			if(hayZombie(this.mapaPlantZombie[i].getF(),j)) 
			{
				tocado(this.mapaPlantZombie[i].getF(),j);
				encontrado = true;
			}
			++j;
		}
	}
	
	private void updateSunflower(Sunflower sunflower) {
		if(this.game.getSunPosition(sunflower.getF(), sunflower.getC()) == null) {
			if (sunflower.getGenerador() == 0)
				game.addSun(sunflower.getF(), sunflower.getC());		
			sunflower.subGenerador();
		}
	}
	
	
	
	////////////////////////////////////////////////////////////
	////////////////////// *** TO STRING*** ////////////////////
	////////////////////////////////////////////////////////////

	public String toStringDebug(int f, int c) {
		StringBuilder builder = new StringBuilder();
		Sun sun = game.getSunPosition(f, c);
		/*S[l:2,x:0,y:0,t:2]*/
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null 
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				builder.append(this.mapaPlantZombie[i].getLetra());
				builder.append("[l:").append(this.mapaPlantZombie[i].getVida());
				builder.append(",x:").append(this.mapaPlantZombie[i].getF());
				builder.append(",y:").append(this.mapaPlantZombie[i].getC());
				builder.append(",t:").append(this.tiempoSiguienteAccion(i));
				if(sun != null)
					builder.append("(").append(sun.getLetra()).append(")");
				else
					builder.append("(").append(" ").append(")");
				builder.append("]");
			}
		}
		return builder.toString();
	}
	
	public String toStringDebugSuns(int f, int c) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[l:").append(" ");
		builder.append(",x:").append(f);
		builder.append(",y:").append(c);
		builder.append(",t:").append(" ");
		builder.append("(").append("*").append(")");
		builder.append("]");
		
		return builder.toString();
	}
	
	private String tiempoSiguienteAccion(int i) {
		StringBuilder builder = new StringBuilder();
		switch(this.mapaPlantZombie[i].getName())
		{
			case "sunflower":
				Sunflower sf = (Sunflower) this.mapaPlantZombie[i];
				builder.append(sf.getGenerador());
				break;
			case "peashooter":
				builder.append("0");
				break;
			case "cherrybomb":
				Cherrybomb cb = (Cherrybomb) this.mapaPlantZombie[i];
				builder.append(cb.getCuentaAtras());
				break;
			case "wallnut":
				builder.append("0");
				break;
			case "zombiecommon":
				ZombieCommon zc = (ZombieCommon) this.mapaPlantZombie[i];
				builder.append(zc.getMovimiento());
				break;
			case "zombiecaracubo":
				ZombieCaracubo zcc = (ZombieCaracubo) this.mapaPlantZombie[i];
				builder.append(zcc.getMovimiento());
				break;
			case "zombiedeportista":
				ZombieDeportista zdp = (ZombieDeportista) this.mapaPlantZombie[i];
				builder.append(zdp.getMovimiento());
				break;
		}	
		return builder.toString();
	}
	public String toStringRelease(int f, int c) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPlantZombie[i] != null 
					&& this.mapaPlantZombie[i].getF() == f 
					&& this.mapaPlantZombie[i].getC() == c) 
			{
				//builder.append("  ");
				builder.append(this.mapaPlantZombie[i].getLetra());
				builder.append('[');
				builder.append(this.mapaPlantZombie[i].getVida());
				builder.append("] ");
			}							
		}
		
		return builder.toString();
	}

}
