package xadrez;

import tabuleirodejogo.Posicao;

public class PosicaoXadrez
{
	private char coluna;
	private int linha;
	public PosicaoXadrez(char coluna, int linha)
	{
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8)
		{
			throw new ExcecaoXadrez("Erro ao instanciar PosicaoXadrez: valores v�lidos de a1 � h8!");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public char getColuna()
	{
		return coluna;
	}
	
	public int getLinha()
	{
		return linha;
	}
	
	// Determina a posi��o na matriz. EX: 00, 01, etc.
	protected Posicao toPosicao()
	{
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	// Determina a posi��o no tabuleiro. Ex: a1, a2, etc.
	protected static PosicaoXadrez fromPosicao(Posicao position)
	{
		return new PosicaoXadrez((char)('a' + position.getColuna()), 8 - position.getLinha());
	}
	
	@Override
	public String toString()
	{
		return " " + coluna + linha;
	}
}
