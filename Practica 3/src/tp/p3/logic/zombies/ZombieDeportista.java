package tp.p3.logic.zombies;

public class ZombieDeportista extends Zombie{
	
	public static final int damage = 1;
	// Se mueve cuando el movimiento llega a 0, despues actualizar movimiento a 2
	public static final int movimiento = 0;
	public static final String nombre = "zombiedeportista";
	public static final String letra = "X";
	public static final int vida = 2;
	
	public ZombieDeportista() {
		super(nombre,vida,damage,movimiento,letra);
	}

	@Override
	public String getInfo() {
		return "[R]unner: Speed: 1 Harm: 1 Life: 2";
	}
}
