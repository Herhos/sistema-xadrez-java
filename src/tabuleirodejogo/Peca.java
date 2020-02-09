package tabuleirodejogo;

public class Peca
{
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro board)
	{
		super();
		this.tabuleiro = board;
		posicao = null;
	}
	
	// Somente classes de dentro do mesmo pacote e subclasses poderão acessar o tabuleiro de uma peça.
	protected Tabuleiro getTabuleiro()
	{
		return tabuleiro;
	}	
}
