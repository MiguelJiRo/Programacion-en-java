package tp.p2.logic;

import tp.p2.utils.MyStringUtils;

public class DebugPrinter extends BoardPrinter implements GamePrinter{
	
	/*
	  	--------------------------------------------------------
		|S[l:2,x:0,y:0,t:2]|W[l:8,x:0,y:4,t:3]|P[l:3,x:1,y:0,t:0]|
		--------------------------------------------------------
		
	 */
	
	final int cellSize = 7;
	final int marginSize = 2;
	final String space = " ";
	final String vDelimiter = "|";
	final String hDelimiter = "-";	

	private boolean vacio = true;
	
	public String printGame(Game game) {
		return encodeGame(game);		
	}

	@Override
	public String encodeGame(Game game) {
		StringBuilder builder = new StringBuilder();
		String margin = MyStringUtils.repeat(this.space, this.marginSize);		
		String lineaseparadora = margin;		
		int contador = 0;		
		
		
		String aux = margin;
		aux += this.vDelimiter;

		for(int i=0; i<game.getFilas(); i++) 
		{			
			for (int j=0; j< game.getColumnas(); j++) 
			{	
				if(game.hayAlgo(i,j)) {		
					aux += game.toStringGameDebug(i, j);
					aux += this.vDelimiter;
					contador++;
				}
			}
		}
		
		for(int k = 0; k < 19*contador;++k)
			lineaseparadora += "-";
		
		
		builder.append(lineaseparadora).append("\n");
		builder.append(aux).append("\n");
		builder.append(lineaseparadora);
		return builder.toString();
		
	}
	
	public boolean comprobarCasoVacio(Game game) {
		int i = 0, j = 0;
		while(i<game.getFilas() && vacio) 
		{			
			while(j< game.getColumnas() && vacio) 
			{	
				if(game.hayAlgo(i,j)) {		
					return false;
				}
				++j;
			}
			++i;
		}
		return true;
	}

}
