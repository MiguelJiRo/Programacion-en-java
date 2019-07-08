package tp.p1.logica;

public class ZombieList {
	private int max;
	private int contador = 0;
	private Zombie[] mapaZombie;
	
	public ZombieList(int max) {
		this.max = max;
		this.mapaZombie = new Zombie[max];
	}
	
	public int getContador() {
		return this.contador;
	}
	
	public void resetZombie() {
		for(int i = 0; i < this.max;++i) 
			this.mapaZombie[i] = null;
		this.contador = 0;
	}
	
	public void addZombie(int f, int c) {
		this.mapaZombie[this.contador] = new Zombie(f,c);
		this.contador++;
	}
	
	public void removeZombie(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaZombie[i] != null 
					&& this.mapaZombie[i].getF() == f 
					&& this.mapaZombie[i].getC() == c) 
			{
				this.mapaZombie[i] = null;
				this.contador--;
			}							
		}		
	}
	
	public boolean hayZombie(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaZombie[i] != null 
					&& this.mapaZombie[i].getF() == f 
					&& this.mapaZombie[i].getC() == c) 
			{
				return true;
			}							
		}	
		return false;
	}
	
	public boolean moverZombie(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaZombie[i] != null 
					&& this.mapaZombie[i].getF() == f 
					&& this.mapaZombie[i].getC() == c) 
			{
				if(this.mapaZombie[i].getMovimiento() == 0)
				{
					this.mapaZombie[i].setMovimiento(1);
					this.mapaZombie[i].setC(c - 1);
					return true;
				}
				else
				{
					this.mapaZombie[i].setMovimiento(this.mapaZombie[i].getMovimiento()-1);
					return false;
				}
			}
		}		
		return false;
	}
	
	public void tocado(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaZombie[i] != null 
					&& this.mapaZombie[i].getF() == f 
					&& this.mapaZombie[i].getC() == c) 
			{
				if(this.mapaZombie[i].getVida() > 1)
				{
					this.mapaZombie[i].setVida(this.mapaZombie[i].getVida()-1);
				}
				else
					this.removeZombie(f, c);
			}							
		}
	}
	
	public String toString(int f, int c) {
		StringBuilder builder = new StringBuilder();
		builder.append("  Z");
		builder.append('[');
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaZombie[i] != null 
					&& this.mapaZombie[i].getF() == f 
					&& this.mapaZombie[i].getC() == c) 
			{
				builder.append(this.mapaZombie[i].getVida());
			}							
		}
		builder.append("] ");
		return builder.toString();
	}
}
