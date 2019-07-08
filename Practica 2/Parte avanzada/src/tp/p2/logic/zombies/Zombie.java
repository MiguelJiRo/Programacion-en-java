package tp.p2.logic.zombies;

import tp.p2.logic.GameObject;

public abstract class Zombie extends GameObject{
	
	public Zombie(String nombre, int vida, int damage, int movimiento,String letra) {
		super(nombre, vida, letra, damage, movimiento);
	}
	
	public abstract String getInfo();
}
