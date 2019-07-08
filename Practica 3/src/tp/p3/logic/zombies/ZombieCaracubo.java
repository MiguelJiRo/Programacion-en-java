package tp.p3.logic.zombies;

public class ZombieCaracubo extends Zombie{
	
	public static final int damage = 1;
	// Se mueve cuando el movimiento llega a 0, despues actualizar movimiento a 2
	public static final int movimiento = 3;
	public static final String nombre = "zombiecaracubo";
	public static final String letra = "W";
	public static final int vida = 8;
	
	public ZombieCaracubo() {
		super(nombre,vida,damage,movimiento,letra);
	}

	@Override
	public String getInfo() {
		return "[B]ucketHead: Speed: 4 Harm: 1 Life: 8";
	}
}
