package tp.p3.control.factory;

import tp.p3.logic.zombies.*;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
			new ZombieCommon(),
			new ZombieCaracubo(),
			new ZombieDeportista()
	};
	
	public static Zombie getZombie(String ZombieName) {
		Zombie zombie = null;
		int i = 0;
		while(i < availableZombies.length) {
			if(availableZombies[i].getName() == ZombieName)
				zombie = availableZombies[i];
			++i;
		}
		return zombie;
	}
	
	public void listOfAvailableZombies() {
		for(int i = 0; i < availableZombies.length;++i) {
			System.out.println(availableZombies[i].getInfo());
		}	
		System.out.println("\n");
	}	
}
