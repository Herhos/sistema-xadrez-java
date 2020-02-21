package xadrez.pecas;

import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;


public class Torre extends PecaXadrez
{
	public Torre(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "T";
	}
}
