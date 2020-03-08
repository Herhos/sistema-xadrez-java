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
		
		// NOROESTE - Lógica para marcar como verdadeiro as posições à noroeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 1); // posicao é um atributo de Peca.
		
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() - 1, auxiliar.getColuna() - 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// NORDESTE - Lógica para marcar como verdadeiro as posições à nordeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 1); // posicao é um atributo de Peca.
				
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() - 1, auxiliar.getColuna() + 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// SUDESTE - Lógica para marcar como verdadeiro as posições à sudeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() +1); // posicao é um atributo de Peca.
		
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() + 1, auxiliar.getColuna() + 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
				
		// SUDOESTE - Lógica para marcar como verdadeiro as posições à sudoeste da peça //
		
		auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 1); // posicao é um atributo de Peca.
						
		// Enquanto a posição auxiliar existir e se não houver uma peça lá, faça!
		while (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.atualizaValores(auxiliar.getLinha() + 1, auxiliar.getColuna() - 1);
		}
		// Se a posição auxiliar existir e houver uma peça oponente, marque-a como verdadeira!
		if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
		{
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		return matriz;
	}
}
