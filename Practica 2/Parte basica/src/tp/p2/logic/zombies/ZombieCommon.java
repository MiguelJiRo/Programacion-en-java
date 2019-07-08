package tp.p2.logic.zombies;

public class ZombieCommon extends Zombie{
	
	public static final int damage = 1;
	// Se mueve cuando el movimiento llega a 0, despues actualizar movimiento a 2
	public static final int movimiento = 1;
	public static final String nombre = "zombiecommon";
	public static final String letra = "Z";
	public static final int vida = 5;
	
	public ZombieCommon() {
		super(nombre,vida,damage,movimiento,letra);
	}

	@Override
	public String getInfo() {
		return "[Z]ombie: Speed: 2 Harm: 1 Life: 5";
	}
}
