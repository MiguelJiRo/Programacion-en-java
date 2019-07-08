package tp.p1.logica;

public class SunflowerList {
	private int max;
	private int contador = 0;
	private Sunflower[] mapaSunflower;
	
	public SunflowerList(int max) {
		this.max = max;
		this.mapaSunflower = new Sunflower[this.max];
	}
	
	public int getContador() {
		return this.contador;
	}
	
	public void resetSunflower() {
		for(int i = 0; i < this.max;++i)
			this.mapaSunflower[i] = null;
		this.contador = 0;
	}
	
	public void addSunflower(int f, int c) {		
		this.mapaSunflower[this.contador] = new Sunflower(f,c);
		this.contador++;
	}
	
	public void removeSunflower(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaSunflower[i] != null 
					&& this.mapaSunflower[i].getF() == f 
					&& this.mapaSunflower[i].getC() == c) 
			{
				this.mapaSunflower[i] = null;
				this.contador--;
			}							
		}
	}
	
	public boolean haySunflower(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaSunflower[i] != null 
					&& this.mapaSunflower[i].getF() == f 
					&& this.mapaSunflower[i].getC() == c) 
			{
				return true;
			}							
		}	
		return false;
	}
	// Función que comprueba para los casos donde Sunflower puede tener más vida
	// Vida por defecto = 1;
	public void tocado(int f, int c) {
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaSunflower[i] != null 
					&& this.mapaSunflower[i].getF() == f 
					&& this.mapaSunflower[i].getC() == c) 
			{
				if(this.mapaSunflower[i].getVida() > 1)
				{
					this.mapaSunflower[i].setVida(this.mapaSunflower[i].getVida()-1);
				}
				else
					this.removeSunflower(f, c);
			}							
		}
	}
	
	public String toString(int f, int c) {
		StringBuilder builder = new StringBuilder();
		builder.append("  S");
		builder.append('[');
		for(int i = 0; i < this.max; ++i) {
			if(this.mapaSunflower[i] != null 
					&& this.mapaSunflower[i].getF() == f 
					&& this.mapaSunflower[i].getC() == c) 
			{
				builder.append(this.mapaSunflower[i].getVida());
			}							
		}
		builder.append("] ");
		return builder.toString();
	}
}
