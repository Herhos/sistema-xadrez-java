package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez
{
	public Rei(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "K";
	}

	private boolean podeMover(Posicao position)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		// NORTE - L�gica para marcar como verdadeiro as posi��es ao norte da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NORDESTE - L�gica para marcar como verdadeiro as posi��es � nordeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// LESTE - L�gica para marcar como verdadeiro as posi��es ao leste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDESTE - L�gica para marcar como verdadeiro as posi��es � sudeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUL - L�gica para marcar como verdadeiro as posi��es ao sul da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDOESTE - L�gica para marcar como verdadeiro as posi��es � sudoeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// OESTE - L�gica para marcar como verdadeiro as posi��es � oeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NOROESTE - L�gica para marcar como verdadeiro as posi��es � noroeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}		
		return matriz;
	}	
}
