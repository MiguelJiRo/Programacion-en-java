package tp.p1.logica;

public class PeashooterList {
	private int max;
	private int contador = 0;
	private Peashooter[] mapaPeashooter;
	
	public PeashooterList(int max) {
		this.max = max;
		this.mapaPeashooter = new Peashooter[this.max];
	}
	
	public void resetPeashooter() {
		for(int i = 0; i < this.max;++i)
			this.mapaPeashooter[i] = null;
		this.contador = 0;
	}
	
	public void addPeashooter(int f, int c) {
		this.mapaPeashooter[this.contador] = new Peashooter(f,c);
		this.contador++;
	}
	
	public void removePeashooter(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPeashooter[i] != null 
					&& this.mapaPeashooter[i].getF() == f 
					&& this.mapaPeashooter[i].getC() == c) 
			{
				this.mapaPeashooter[i] = null;
				this.contador--;
			}							
		}
	}
	
	public boolean hayPeashooter(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPeashooter[i] != null 
					&& this.mapaPeashooter[i].getF() == f 
					&& this.mapaPeashooter[i].getC() == c) 
			{
				return true;
			}							
		}	
		return false;
	}
	
	public void tocado(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPeashooter[i] != null 
					&& this.mapaPeashooter[i].getF() == f 
					&& this.mapaPeashooter[i].getC() == c) 
			{
				if(this.mapaPeashooter[i].getVida() > 1)
				{
					this.mapaPeashooter[i].setVida(this.mapaPeashooter[i].getVida()-1);
				}
				else
					this.removePeashooter(f, c);
			}							
		}
	}
	
	public String toString(int f, int c) {
		StringBuilder builder = new StringBuilder();
		builder.append("  P");
		builder.append('[');
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaPeashooter[i] != null 
					&& this.mapaPeashooter[i].getF() == f 
					&& this.mapaPeashooter[i].getC() == c) 
			{
				builder.append(this.mapaPeashooter[i].getVida());
			}							
		}
		builder.append("] ");
		return builder.toString();
	}
}
