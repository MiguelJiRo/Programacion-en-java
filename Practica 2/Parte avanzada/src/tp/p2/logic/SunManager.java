package tp.p2.logic;

import java.util.Random;

import tp.p2.logic.pasives.Sun;

public class SunManager {
	int contador;
	private int max = 50;
	private Sun[] suns;
	private int posicionNuevo = 0;
	
	private int fila,columna;
	
	public SunManager(int f, int c) {
		this.fila = f;
		this.columna = c;
		this.contador = 5000;
		this.suns = new Sun[this.max];
	}
	
	public int getSuncoin() {
		return this.contador;
	}
	
	public void resetSuncoin() {
		this.contador = 5000;
	}
	
	public void add10Suncoins() {
		this.contador += 10;
	}
	
	public boolean subNSuncoin(int n) {
		boolean correcto = false;
		if(this.contador >= n) {
			this.contador -= n;
			correcto = true;
		}			
		return correcto;
	}
	
	////////////////////////////////////////////////////////////	
	//////////////////// *** SUNS PR2 *** //////////////////////
	////////////////////////////////////////////////////////////
	
	public void addSun(Sun sun) {
		this.suns[this.posicionNuevo] = sun;
		this.posicionNuevo++;
	}
	
	public void removeSun(int f, int c) {
		boolean salir = false;
		for(int i = 0; i < this.posicionNuevo && !salir;++i) {
			if(this.suns[i].getF() == f
					&& this.suns[i].getC() == c) {
				recolocarSuns(i);
				salir = true;
			}
		}		
	}
	
	private void recolocarSuns(int i) {
		for(int j = i; j < this.posicionNuevo - 1; ++j) {
			this.suns[j] = this.suns[j+1];
		}
		this.posicionNuevo--;
	}
	
	public Sun getPositionSun(int f, int c) {
		for(int i = 0; i < this.posicionNuevo;++i) {
			if(this.suns[i].getF() == f
					&& this.suns[i].getC() == c)
				return this.suns[i];
		}
		return null;
	}
	
	public void update(Game game) {
		Random ran = new Random();
		if(game.getCiclos() % 5 == 0
				&& game.getCiclos() > 0)
		{
			int f = ran.nextInt(this.fila);
			int c = ran.nextInt(this.columna);
			
			if(posicionValida(f,c))
				game.addSun(f,c);
		}			
	}
	
	private boolean posicionValida (int f,int c) {
		for(int i = 0; i < this.posicionNuevo; ++i) {
			if(this.suns[i].getF() == f 
					&& this.suns[i].getC() == c)
				return false;
		}
		return true;
	}
	
	public boolean catchSun(int f, int c) {
		Sun sun = (Sun) getPositionSun(f, c);
			if(sun != null)
			{
				this.removeSun(f, c);
				this.add10Suncoins();
				return true;
			}
		return false;
	}
}