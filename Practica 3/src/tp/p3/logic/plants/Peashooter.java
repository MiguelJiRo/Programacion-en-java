package tp.p3.logic.plants;

public class Peashooter extends Plant {
	
	public static final int vida = 3;
	public static final int coste = 50;
	public static final String nombre = "peashooter";
	public static final String letra = "P";
	
	public Peashooter() {
		super(nombre,vida,coste,letra);
	}

	@Override
	public String getInfo() {
		return "[P]eashooter: Cost 50 suncoins Harm: 1";
	}
}
