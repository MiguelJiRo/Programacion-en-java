package tp.p3.logic.plants;

public class Cherrybomb extends Plant{

	public static final int vida = 2;
	public static final int coste = 50;
	public static final String nombre = "cherrybomb";
	public static final String letra = "C";
	
	private int cuentaAtras = 2;
	
	public Cherrybomb() {
		super(nombre,vida,coste,letra);
	}
	
	public int getCuentaAtras() {
		return this.cuentaAtras;
	}
	
	public void subCuentaAtras() {
		this.cuentaAtras--;
	}

	@Override
	public String getInfo() {
		return "Peta[c]ereza: Cost: 50 suncoins Harm: 10";
	}
}
