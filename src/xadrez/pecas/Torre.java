package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez
{
	public Torre(Tabuleiro board, Cor color)
	{
		super(board, color);		
	}
	
	@Override
	public String toString()
	{
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		// ACIMA - L�gica para marcar como verdadeiro as posi��es acima da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna()); // posicao � um atributo de Peca.
		
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() - 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// ABAIXO - L�gica para marcar como verdadeiro as posi��es abaixo da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna()); // posicao � um atributo de Peca.
		
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() + 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// ESQUERDA - L�gica para marcar como verdadeiro as posi��es � esquerda da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() - 1); // posicao � um atributo de Peca.
				
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() - 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// DIREITA - L�gica para marcar como verdadeiro as posi��es � direita da pe�a //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() + 1); // posicao � um atributo de Peca.
						
		// Enquanto a posi��o auxiliar existir e se n�o houver uma pe�a l�, fa�a!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() + 1);
		}
		// Se a posi��o auxiliar existir e houver uma pe�a oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		return matriz;
	}
}
