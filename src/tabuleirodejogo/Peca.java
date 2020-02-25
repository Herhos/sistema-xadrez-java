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
	
	// Somente classes de dentro do mesmo pacote e subclasses poderão acessar o tabuleiro de uma peça.
	protected Tabuleiro getTabuleiro()
	{
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean seMove(Posicao position)
	{
		/* Hook method: é um método que engancha a subclasse. Nesse caso,
		 * o método concreto seMove() está fazendo referência ao método abstrato
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
