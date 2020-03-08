package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez
{
	public Bispo(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		// NOROESTE - L�gica para marcar como verdadeiro as posi��es � noroeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 1); // posicao � um atributo de Peca.
		
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() - 1, auxiliar.getColuna() - 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NORDESTE - L�gica para marcar como verdadeiro as posi��es � nordeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 1); // posicao � um atributo de Peca.
				
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() - 1, auxiliar.getColuna() + 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDESTE - L�gica para marcar como verdadeiro as posi��es � sudeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() +1); // posicao � um atributo de Peca.
		
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() + 1, auxiliar.getColuna() + 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
				
		// SUDOESTE - L�gica para marcar como verdadeiro as posi��es � sudoeste da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 1); // posicao � um atributo de Peca.
						
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() + 1, auxiliar.getColuna() - 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		return matriz;
	}
}
