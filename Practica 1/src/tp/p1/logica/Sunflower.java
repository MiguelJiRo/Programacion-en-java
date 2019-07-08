package tp.p1.logica;

public class Sunflower {
	private int x;
	private int y;
	private int vida;
	private int coste;
	
	public Sunflower(int f, int c) {
		this.x = f;
		this.y = c;
		this.vida = 1;
		this.coste = 20;
	}
	
	public void setF(int f) {
		this.x = f;
	}
	
	public void setC(int c) {
		this.y = c;
	}
	
	public int getF() {
		return this.x;
	}
	
	public int getC() {
		return this.y;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public void setVida(int v) {
		this.vida = v;
	}
	
	public int getCoste() {
		return this.coste;
	}
}
