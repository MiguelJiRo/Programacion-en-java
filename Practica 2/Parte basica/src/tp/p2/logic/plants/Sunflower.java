package tp.p2.logic.plants;

public class Sunflower extends Plant{
		
	private int generador;
	private static final int vida = 1;
	private static final int coste = 20;
	private static final String nombre = "sunflower";
	private static final String letra = "S";
	private final int numTurnosGenerador = 1;
	
	public Sunflower() {
		super(nombre, vida, coste, letra);
	}
	
	public void iniGenerador() {
		this.generador = this.numTurnosGenerador;
	}
	
	public void setGenerador(int n) {
		this.generador = n;
	}
	
	public int getGenerador() {
		return this.generador;
	}
	
	public void subGenerador() {
		if(this.generador == 0)
			iniGenerador();
		else
			--this.generador;
	}

	@Override
	public String getInfo() {
		return "[S]unflower: Cost 20 suncoins Harm: 0";
	}
}
