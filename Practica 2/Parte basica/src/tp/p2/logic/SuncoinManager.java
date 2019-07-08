package tp.p2.logic;

public class SuncoinManager {
	int contador;
	
	public SuncoinManager() {
		this.contador = 50;
	}
	
	public int getSuncoin() {
		return this.contador;
	}
	
	public void resetSuncoin() {
		this.contador = 50;
	}
	
	public void add10Suncoins() {
		this.contador += 10;
	}
	
	public boolean subNSuncoin(int n) {
		boolean correcto = false;
		if(this.contador >= n) {
			this.contador -= n;
			correcto = true;
		}			
		return correcto;
	}
}