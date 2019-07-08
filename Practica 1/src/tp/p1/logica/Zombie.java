package tp.p1.logica;

public class Zombie {
	private int x;
	private int y;
	private int vida;
	private int damage;
	// Se mueve cuando el movimiento llega a 0, despues actualizar movimiento a 2
	private int movimiento;
	
	public Zombie(int f, int c) {
		this.x = f;
		this.y = c;
		this.vida = 5;
		this.damage = 1;
		this.movimiento = 1;
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
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getMovimiento() {
		return this.movimiento;
	}
	
	public void setMovimiento(int n) {
		this.movimiento = n;
	}
	
	
}
