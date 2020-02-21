package xadrez.pecas;

import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez
{
	public Rei(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "K";
	}	
}
