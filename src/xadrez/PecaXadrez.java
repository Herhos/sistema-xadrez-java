package xadrez;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;

public abstract class PecaXadrez extends Peca
{
	private Cor cor;
	private int contaMovimentos; // Propriedade "int" por padr�o inicia com zero e n�o precisa ser iniciado no construtor.

	public PecaXadrez(Tabuleiro board, Cor color)
	{
		super(board);
		this.cor = color;
	}

	public Cor getCor()
	{
		return cor;
	}
	
	public int getContaMovimentos()
	{
		return contaMovimentos;
	}
	
	public void aumentaContaMovimentos()
	{
		contaMovimentos++;
	}
	
	public void diminuiContaMovimentos()
	{
		contaMovimentos--;
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
