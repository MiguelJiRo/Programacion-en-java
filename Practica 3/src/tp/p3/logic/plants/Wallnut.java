package tp.p3.logic.plants;

public class Wallnut  extends Plant{

	public static final int vida = 10;
	public static final int coste = 50;
	public static final String nombre = "wallnut";
	public static final String letra = "N";
	
	public Wallnut() {
		super(nombre,vida,coste,letra);
	}

	@Override
	public String getInfo() {
		return "[N]uez: Cost: 50 suncoins Harm: 0";
	}
}
