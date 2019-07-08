package tp.p3.logic;

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
	
	public void setNumZ(int numero) {
		this.numZ = numero;
	}
	
	public void resetZombieManager(Level level) {
		this.numZ = level.getNumZombies();
	}
	
	public void subNumZ() {
		this.numZ--;
	}

	public boolean isZombieAdded(int seed) {
		// nextDouble valor entre 0.0 y 1.0
		double rand = ran.nextDouble();
		if(rand <= this.frec*seed)
			return true;
		return false;
		
	}
	
	public int whichZombie() {
		int wz = this.ran.nextInt(3);
		return wz;
	}
	
	public int whereAddZombie(int channel) {
		return this.ran.nextInt(channel);
	}
	
	public boolean subZombies() {
		if (this.numZ <= 0)
			return false;

		this.numZ--;
		return true;
	}
}