package tabuleirodejogo;

public abstract class Peca
{
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro board)
	{
		this.tabuleiro = board;
		posicao = null;
	}
	
	// Somente classes de dentro do mesmo pacote e subclasses poder�o acessar o tabuleiro de uma pe�a.
	protected Tabuleiro getTabuleiro()
	{
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean seMove(Posicao position)
	{
		/* Hook method: � um m�todo que engancha a subclasse. Nesse caso,
		 * o m�todo concreto seMove() est� fazendo refer�ncia ao m�todo abstrato
		 * movimentosPossiveis(). */
		return movimentosPossiveis()[position.getLinha()][position.getColuna()];
	}
	
	public boolean pecaPresa()
	{
		boolean [][] matriz = movimentosPossiveis();
		for (int linha = 0; linha < matriz.length; linha++)
		{
			for (int coluna = 0; coluna < matriz.length; coluna++)
			{
				if (matriz[linha][coluna])
				{
					return true;
				}
			}
		}
		return false;
	}
}
