package xadrez;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;

public abstract class PecaXadrez extends Peca
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
	
	public PosicaoXadrez getPosicaoXadrez()
	{
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao position)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(position);
		return p != null && p.getCor() != cor;
	}
}
