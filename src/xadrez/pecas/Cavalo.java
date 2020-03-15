package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez
{
	public Cavalo(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "C";
	}

	// Corresponde ao canMove do exercício.
	private boolean podeMover(Posicao position)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}
	
	// Corresponde ao possibleMoves do exercício.
	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		// 8 posições possíveis no sentido horário.
		
		// P1 - Lógica para marcar como verdadeiro as posições P1 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P2 - Lógica para marcar como verdadeiro as posições P2 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P3 - Lógica para marcar como verdadeiro as posições P3 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P4 - Lógica para marcar como verdadeiro as posições P4 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P5 - Lógica para marcar como verdadeiro as posições P5 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P6 - Lógica para marcar como verdadeiro as posições P6 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P7 - Lógica para marcar como verdadeiro as posições P7 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P8 - Lógica para marcar como verdadeiro as posições P8 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}		
		return matriz;
	}	
}
