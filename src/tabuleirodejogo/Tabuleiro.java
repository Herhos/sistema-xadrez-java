package tabuleirodejogo;

public class Tabuleiro
{
	// Atributos
	private int linhas;
	private int colunas;
	private Peca [][] pecas;
	
	// Contrutor
	public Tabuleiro(int rows, int columns)
	{
		super();
		this.linhas = rows;
		this.colunas = columns;
		pecas = new Peca[rows][columns];
	}
	
	// Getters e Setters
	public int getLinhas()
	{
		return linhas;
	}
	public void setLinhas(int rows)
	{
		this.linhas = rows;
	}
	public int getColunas()
	{
		return colunas;
	}
	public void setColunas(int columns)
	{
		this.colunas = columns;
	}
	
	// Métodos específicos
	public Peca peca(int rown, int column)
	{
		return pecas[rown][column];
	}
	
	public Peca peca(Posicao position)
	{
		return pecas[position.getLinha()][position.getColuna()];
	}
	
	public void colocarPeca(Peca piece, Posicao position)
	{
		pecas[position.getLinha()][position.getColuna()] = piece;
		piece.posicao = position;
		//Acesso ao atributo posicao da classe Peca, por estar no mesmo pacote.
	}
}
