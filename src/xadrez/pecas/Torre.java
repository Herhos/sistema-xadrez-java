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
		
		// ACIMA - Lógica para marcar como verdadeiro as posições acima da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna()); // posicao é um atributo de Peca.
		
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() - 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// ABAIXO - Lógica para marcar como verdadeiro as posições abaixo da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna()); // posicao é um atributo de Peca.
		
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() + 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// ESQUERDA - Lógica para marcar como verdadeiro as posições à esquerda da peça //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() - 1); // posicao é um atributo de Peca.
				
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() - 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// DIREITA - Lógica para marcar como verdadeiro as posições à direita da peça //
		
		auxiliar.atualizaValores(posicao.getLinha(), posicao.getColuna() + 1); // posicao é um atributo de Peca.
						
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() + 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		return matriz;
	}
}
