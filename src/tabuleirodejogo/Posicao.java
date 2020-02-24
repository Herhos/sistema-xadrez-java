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

	public void setLinha(int rown)
	{
		this.linha = rown;
	}

	public int getColuna()
	{
		return coluna;
	}

	public void setColuna(int column)
	{
		this.coluna = column;
	}

	@Override
	public String toString()
	{
		return linha + ", " + coluna;
	}	
}
