package xadrez;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Tabuleiro;

public class PecaXadrez extends Peca
{
	private Cor cor;

	public PecaXadrez(Tabuleiro board, Cor color)
	{
		super(board);
		this.cor = color;
	}

	public Cor getCor()
	{
		return cor;
	}	
}
