package tabuleirodejogo;

public class Tabuleiro
{
	private int linhas;
	private int colunas;
	private Peca [][] pecas;
	
	public Tabuleiro(int rows, int columns)
	{
		super();
		this.linhas = rows;
		this.colunas = columns;
		pecas = new Peca[rows][columns];
	}
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
}
