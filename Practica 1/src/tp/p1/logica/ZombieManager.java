package tp.p1.logica;

import java.util.Random;

public class ZombieManager {

	int numZ;
	double frec;
	int channel;

	Random ran = new Random();
	
	public ZombieManager(Level level) {
		this.numZ = level.getNumZombies();
		this.frec = level.getFrecuencia();
	}
	
	public int getNumZ() {
		return this.numZ;
	}
	
	public void subNumZ() {
		this.numZ--;
	}

	public boolean isZombieAdded() {
		// nextDouble valor entre 0.0 y 1.0
		double rand = ran.nextDouble();
		if(rand <= this.frec)
			return true;
		return false;
		
	}
	
	public int whereAddZombie(int channel) {
		int caminoRandom = this.ran.nextInt(channel + 1);
		return caminoRandom;
	}
	
	public boolean subZombies() {
		if (this.numZ <= 0)
			return false;

		this.numZ--;
		return true;
	}
}