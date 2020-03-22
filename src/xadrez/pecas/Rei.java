package xadrez.pecas;

import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez
{
	// Depend�ncia de Rei com PartidaXadrez.
	
	private PartidaXadrez chessMatch;
	
	public Rei(Tabuleiro board, Cor color, PartidaXadrez chessMatch)
	{
		super(board, color);
		this.chessMatch = chessMatch;
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
	
	private boolean testaRoqueTorre(Posicao position)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(position);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContaMovimentos() == 0;
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
		
		// Teste do movimento especial Roque
		
		if (getContaMovimentos() == 0 && !chessMatch.getXeque())
		{
			// Testanto o Roque pequeno (castling kingside rook).
			
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			
			if (testaRoqueTorre(posT1)) // Testando se a Torre est� apta ao Roque.
			{
				Posicao posDireita1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao posDireita2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				
				if (getTabuleiro().peca(posDireita1) == null && getTabuleiro().peca(posDireita2) == null)
				{
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			
			// Testanto o Roque grande (castling queenside rook).
			
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			
			if (testaRoqueTorre(posT2)) // Testando se a Torre est� apta ao Roque.
			{
				Posicao posEsquerda1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao posEsquerda2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao posEsquerda3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				
				if (getTabuleiro().peca(posEsquerda1) == null && getTabuleiro().peca(posEsquerda2) == null
					&& getTabuleiro().peca(posEsquerda3) == null)
				{
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}			
		}
		
		return matriz;
	}	
}
