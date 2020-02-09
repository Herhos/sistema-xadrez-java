package tabuleirodejogo;

public class Posicao
{
	private int linha;
	private int coluna;
	
	public Posicao(int rown, int column)
	{
		super();
		this.linha = rown;
		this.coluna = column;
	}

	public int getLinha()
	{
		return linha;
	}

	public void setLinha(int linha)
	{
		this.linha = linha;
	}

	public int getColuna()
	{
		return coluna;
	}

	public void setColuna(int coluna)
	{
		this.coluna = coluna;
	}

	@Override
	public String toString()
	{
		return linha + ", " + coluna;
	}
	
	
}
