package xadrez;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez
{
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez()
	{
		tabuleiro = new Tabuleiro(8, 8);
		ajusteInicial();
	}
	
	public PecaXadrez[][] getPecas()
	{
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int linha = 0; linha < tabuleiro.getLinhas(); linha++)
		{
			for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna++)
			{
				matriz[linha][coluna] = (PecaXadrez) tabuleiro.peca(linha, coluna);
			}
		}
		return matriz;
	}
	
	private void ajusteInicial()
	{
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
	}
}
