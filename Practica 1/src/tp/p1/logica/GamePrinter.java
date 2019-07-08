package tp.p1.logica;

public class GamePrinter {
	
	private int cellSize = 7;
	private String space = " ";
	private String vDelimiter = "|";
	private String hDelimiter = "-";

	private int filas;
	private int columnas;
	
	private Game game;
	
	public GamePrinter(int f, int c, Game game) {
		this.filas = f;
		this.columnas = c;
		this.game = game;
	}
	
	
	/**
	 * Metodo que dibuja el tablero de juego.
	 * @return String con la información del tablero del juego.
	 */
	public String toString1(String v[][]) {
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
	public String toString(String v[][]) {
		StringBuilder builder = new StringBuilder();
		
		builder.append('\n');
		////////////////////////////////////////////////////////////////
		// primera linea de "1234567..."
		////////////////////////////////////////////////////////////////
		builder.append(this.space);
		builder.append(this.space);
		for(int j = 0; j < this.columnas; ++j) 
		{
			for(int k = 0; k < this.cellSize ; ++k) {
				builder.append(this.space);	
				if(k==this.cellSize/2)
					builder.append(j);
			}
		}
		builder.append('\n');
		////////////////////////////////////////////////////////////////
		// primera linea de "----"
		////////////////////////////////////////////////////////////////
		builder.append(this.space);
		builder.append(this.space);
		builder.append(this.space);
		for(int j = 0; j <= this.columnas; ++j) 
		{
			for(int k = 0; k < this.cellSize ; ++k) {
				builder.append(this.hDelimiter);
			}
		}
		builder.append('\n');
		////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < this.filas; ++i) 
		{	
			builder.append(i);
			builder.append(this.space);
			////////////////////////////////////////////////////////////////
			// linea de juego - Plants && Zombies
			////////////////////////////////////////////////////////////////
			for(int j = 0; j < this.columnas; ++j) 
			{
				builder.append(this.vDelimiter);
				if(this.game.comprobarSunflower(i, j))
					builder.append(v[i][j]);
				else if(this.game.comprobarPeashooter(i, j))
					builder.append(v[i][j]);
				else if(this.game.comprobarZombie(i,j))
					builder.append(v[i][j]);
				else
				{
					for(int k = 0; k < this.cellSize ; ++k) {
						builder.append(this.space);			
					}
				}
			}
			builder.append(this.vDelimiter);
			builder.append('\n');
			////////////////////////////////////////////////////////////////
			// linea de "----"
			////////////////////////////////////////////////////////////////
			builder.append(this.space);
			builder.append(this.space);
			builder.append(this.space);
			for(int j = 0; j <= this.columnas; ++j) 
			{
				for(int k = 0; k < this.cellSize ; ++k) {
					builder.append(this.hDelimiter);
				}
			}
			builder.append('\n');
			////////////////////////////////////////////////////////////////
		}		
		return builder.toString();
	}
}