package tp.p2.logic;

import tp.p2.utils.MyStringUtils;

public class ReleasePrinter extends BoardPrinter implements GamePrinter{

	final int cellSize = 7;
	final int marginSize = 2;
	final String space = " ";
	final String vDelimiter = "|";
	final String hDelimiter = "-";
	
	public String printGame(Game game) {
		return encodeGame(game);
	}

	@Override
	public String encodeGame(Game game) {		
		
		StringBuilder builder = new StringBuilder();
		
		String rowDelimiter = MyStringUtils.repeat(this.hDelimiter, (game.getColumnas() * (this.cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(this.space, this.marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + this.space, rowDelimiter);
		
		builder.append(this.numbersH(game));
		builder.append(lineDelimiter);
		
		for(int i=0; i<game.getFilas(); i++) 
		{
			builder.append(margin).append(this.vDelimiter);
			for (int j=0; j<game.getColumnas(); j++) 
			{	
				if(game.hayAlgo(i,j))
					builder.append( MyStringUtils.centre(game.toStringGameRelease(i, j), this.cellSize)).append(this.vDelimiter);
				else
					builder.append( MyStringUtils.centre(this.space, this.cellSize)).append(this.vDelimiter);
			}
			builder.append(this.space).append(this.space).append(i);
			builder.append(lineDelimiter);
		}
		return builder.toString();
	}
	
	private String numbersH(Game game) {
		StringBuilder number = new StringBuilder();
		number.append(this.space).append(this.space);
		for(int j = 0; j < game.getColumnas(); ++j) 
		{
			for(int k = 0; k < this.cellSize ; ++k) {
				number.append(this.space);	
				if(k==this.cellSize/2)
					number.append(j);
			}
		}
		return number.toString();
	}

}
