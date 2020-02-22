package tabuleirodejogo;

public class Tabuleiro
{
	// Atributos
	private int linhas;
	private int colunas;
	private Peca [][] pecas;
	
	// Construtor
	public Tabuleiro(int rows, int columns)
	{
		if (rows < 1 || columns < 1)
		{
			throw new ExcecaoTabuleiro("Erro ao criar tabuleiro: É necessário ao menos uma linha e uma coluna!");
		}
		this.linhas = rows;
		this.colunas = columns;
		pecas = new Peca[rows][columns];
	}
	
	// Getters e Setters
	public int getLinhas()
	{
		return linhas;
	}	
	public int getColunas()
	{
		return colunas;
	}
		
	// Métodos específicos
	public Peca peca(int row, int column)
	{
		if (!existePosicao1(row, column))
		{
			throw new ExcecaoTabuleiro("Esta posição não existe no tabuleiro!");
		}
		return pecas[row][column];
	}
	
	public Peca peca(Posicao position)
	{
		if (!existePosicao2(position))
		{
			throw new ExcecaoTabuleiro("Esta posição não existe no tabuleiro!");
		}
		return pecas[position.getLinha()][position.getColuna()];
	}
	
	public void colocarPeca(Peca piece, Posicao position)
	{
		if (existePeca(position))
		{
			throw new ExcecaoTabuleiro("Já existe uma peça nessa posição: " + position);
		}
		pecas[position.getLinha()][position.getColuna()] = piece;
		piece.posicao = position;
		//Acesso ao atributo posicao da classe Peca, por estar no mesmo pacote.
	}
	
	private boolean existePosicao1(int linha, int coluna)
	{
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas; 
	}
	
	public boolean existePosicao2(Posicao position)
	{
		return existePosicao1(position.getLinha(), position.getColuna());
	}
	
	public boolean existePeca(Posicao position)
	{
		if (!existePosicao2(position))
		{
			throw new ExcecaoTabuleiro("Esta posição não existe no tabuleiro!");
		}
		return peca(position) != null;
	}
}
