package tp.p2.logic.plants;

import tp.p2.logic.GameObject;

public abstract class Plant extends GameObject{	
	
	public Plant(String nombre, int vida, int coste, String letra) {
		super(nombre, vida, letra, coste);
	}		
	
	public abstract String getInfo();
		
}
