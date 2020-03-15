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

	// Corresponde ao canMove do exerc�cio.
	private boolean podeMover(Posicao position)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}
	
	// Corresponde ao possibleMoves do exerc�cio.
	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		// 8 posi��es poss�veis no sentido hor�rio.
		
		// P1 - L�gica para marcar como verdadeiro as posi��es P1 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P2 - L�gica para marcar como verdadeiro as posi��es P2 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P3 - L�gica para marcar como verdadeiro as posi��es P3 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P4 - L�gica para marcar como verdadeiro as posi��es P4 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P5 - L�gica para marcar como verdadeiro as posi��es P5 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P6 - L�gica para marcar como verdadeiro as posi��es P6 //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P7 - L�gica para marcar como verdadeiro as posi��es P7 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// P8 - L�gica para marcar como verdadeiro as posi��es P8 //
		
		auxiliar.atualizaValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao2(auxiliar) && podeMover(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}		
		return matriz;
	}	
}
