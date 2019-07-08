package tp.p1.logica;

public enum Level {
	// Easy Nª Zombies - 3 Frecuencia - 0.1
	// Hard Nª Zombies - 5 Frecuencia - 0.2
	// Insane Nª Zombies - 10 Frecuencia - 0.3
	EASY("easy",3,0.1),
	HARD("hard",5,0.2),
	INSANE("insane",10,0.3);
	
	private String n;
	private int numZombies;
	private double frec;
	
	private Level(String nivel, int numZombies, double frec) {
		this.n = nivel;
		this.numZombies = numZombies;
		this.frec = frec;
	}
	
	public String getLevel() {
		return this.n;
	}
	
	public int getNumZombies() {
		return this.numZombies;
	}
	
	public double getFrecuencia() {
		return this.frec;
	}
	
	public static Level fromParamL(String param) {
		for (Level level : Level.values()) {
			if (level.n.equals(param))
				return level;
		}
		return null;
	}	
}
