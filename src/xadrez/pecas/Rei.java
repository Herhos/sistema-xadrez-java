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
		
		// NORTE - Lógica para marcar como verdadeiro as posições ao norte da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NORDESTE - Lógica para marcar como verdadeiro as posições à nordeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// LESTE - Lógica para marcar como verdadeiro as posições ao leste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDESTE - Lógica para marcar como verdadeiro as posições à sudeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUL - Lógica para marcar como verdadeiro as posições ao sul da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDOESTE - Lógica para marcar como verdadeiro as posições à sudoeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// OESTE - Lógica para marcar como verdadeiro as posições à oeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NOROESTE - Lógica para marcar como verdadeiro as posições à noroeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}		
		return matriz;
	}	
}
