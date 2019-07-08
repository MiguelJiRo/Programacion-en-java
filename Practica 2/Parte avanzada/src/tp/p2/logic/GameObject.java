package tp.p2.logic;

public abstract class GameObject {
	
	protected String nombre;
	protected String letra;	
	protected int vida;	
	
	private int fila;
	private int columna;	
	
	private int coste;
	
	// Se mueve cuando el movimiento llega a 0, despues actualizar movimiento a 2
	protected int movimiento;
	protected int damage;
	
	// Plant
	public GameObject(String nombre, int vida, String letra, int coste) {
		this.nombre = nombre;
		this.vida = vida;
		this.letra = letra;
		this.coste = coste;
	}
	
	// Zombie
	public GameObject(String nombre, int vida, String letra, int damage, int movimiento) {
		this.nombre = nombre;
		this.vida = vida;
		this.letra = letra;
		this.damage = damage;
		this.movimiento = movimiento;
	}
	
	//Pasive
	public GameObject(String nombre, String letra) {
		this.nombre = nombre;
		this.letra = letra;
	}
	
	////////////////////////////////////////////////////////////
	///////////////////// *** Generales *** ////////////////////
	////////////////////////////////////////////////////////////

	public void setF(int f) {
		this.fila = f;
	}
	
	public int getF() {
		return this.fila;
	}
	
	public void setC(int c) {
		this.columna = c;
	}	
	
	public int getC() {
		return this.columna;
	}
	
	public void setVida(int v) {
		this.vida = v;
	}
	
	public int getVida() {
		return this.vida;
	}	
	
	public String getName() {
		return this.nombre;
	}
	
	public String getLetra() {
		return this.letra;
	}
	
	////////////////////////////////////////////////////////////
	/////////////////////// *** Plant *** //////////////////////
	////////////////////////////////////////////////////////////
	
	public int getCoste() {
		return this.coste;
	}
	
	////////////////////////////////////////////////////////////
	/////////////////////// *** Zombie *** /////////////////////
	////////////////////////////////////////////////////////////
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getMovimiento() {
		return this.movimiento;
	}
	
	public void setMovimiento(int n) {
		this.movimiento = n;
	}	
	
	////////////////////////////////////////////////////////////
	///////////////////// *** Abstract *** /////////////////////
	////////////////////////////////////////////////////////////
	
	
}
