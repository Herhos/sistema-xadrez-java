package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez
{
	// Dependência de Peao com PartidaXadrez.
	
	public PartidaXadrez chessMatch;
	
	public Peao(Tabuleiro board, Cor color, PartidaXadrez chessMatch)
	{
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString()
	{
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCO)
		{
			// Testa se está livre uma casa à frente
			auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Testa se está livre duas casas à frente
			auxiliar.atualizaValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao auxiliar2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar) &&
				getTabuleiro().existePosicao2(auxiliar2) && !getTabuleiro().existePeca(auxiliar2) &&
				getContaMovimentos() == 0)
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Testa se a diagonal esquerda está livre
			auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Testa se a diagonal direita está livre
			auxiliar.atualizaValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// En Passant para brancas
			
			if (posicao.getLinha() == 3)
			{
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existePosicao2(esquerda) && existePecaOponente(esquerda)
					&& getTabuleiro().peca(esquerda) == chessMatch.getEnPassantVulneravel())
				{
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existePosicao2(direita) && existePecaOponente(direita)
					&& getTabuleiro().peca(direita) == chessMatch.getEnPassantVulneravel())
				{
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		}
		else // Testando as peças pretas
		{
			// Testa se está livre uma casa à frente
			auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Testa se está livre duas casas à frente
			auxiliar.atualizaValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao auxiliar2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().existePosicao2(auxiliar) && !getTabuleiro().existePeca(auxiliar) &&
				getTabuleiro().existePosicao2(auxiliar2) && !getTabuleiro().existePeca(auxiliar2) &&
				getContaMovimentos() == 0)
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Testa se a diagonal esquerda está livre
			auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
						
			// Testa se a diagonal direita está livre
			auxiliar.atualizaValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().existePosicao2(auxiliar) && existePecaOponente(auxiliar))
			{
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// En Passant para pretas
			
			if (posicao.getLinha() == 4)
			{
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existePosicao2(esquerda) && existePecaOponente(esquerda)
					&& getTabuleiro().peca(esquerda) == chessMatch.getEnPassantVulneravel())
				{
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existePosicao2(direita) && existePecaOponente(direita)
					&& getTabuleiro().peca(direita) == chessMatch.getEnPassantVulneravel())
				{
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}		
		return matriz;
	}	
}
