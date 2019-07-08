package tp.p3.logic.zombies;

import tp.p3.logic.GameObject;

public abstract class Zombie extends GameObject{
	
	public Zombie(String nombre, int vida, int damage, int movimiento,String letra) {
		super(nombre, vida, letra, damage, movimiento);
	}
	
	public abstract String getInfo();
}
