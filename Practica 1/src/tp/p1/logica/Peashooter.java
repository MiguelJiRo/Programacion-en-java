package tp.p1.logica;

public class Peashooter {
	private int x;
	private int y;
	private int vida;
	private int coste;
	
	public Peashooter(int f, int c) {
		this.x = f;
		this.y = c;
		this.vida = 3;
		this.coste = 50;
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
	
	public void setVida(int n) {
		this.vida = n;
	}
	
	public int getCoste() {
		return this.coste;
	}
}
