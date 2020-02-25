package xadrez;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez
{
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez()
	{
		tabuleiro = new Tabuleiro(8, 8);
		ajusteInicial();
	}
	
	public PecaXadrez[][] getPecas()
	{
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int linha = 0; linha < tabuleiro.getLinhas(); linha++)
		{
			for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna++)
			{
				matriz[linha][coluna] = (PecaXadrez) tabuleiro.peca(linha, coluna);
			}
		}
		return matriz;
	}
	
	public PecaXadrez executaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino)
	{
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem, destino);
		Peca pecaCapturada = movaPeca(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao position)
	{
		if (!tabuleiro.existePeca(position))
		{
			throw new ExcecaoXadrez("N�o existe uma pe�a na posi��o de origem. Tecle ENTER para retornar!");
		}
		
		// Se a pe�a estiver presa em determinada posi��o do tabuleiro, lan�ar uma exce��o.
		if (!tabuleiro.peca(position).pecaPresa())
		{
			throw new ExcecaoXadrez("N�o existem movimentos poss�veis dispon�veis!");
		}
	}
	
	private void validaPosicaoDestino(Posicao source, Posicao target)
	{
		if (!tabuleiro.peca(source).seMove(target))
		{
			throw new ExcecaoXadrez("A pe�a escolhida n�o pode ser movida"
				+ " para a posi��o de destino!");
		}
	}
	
	private Peca movaPeca(Posicao origem, Posicao destino)
	{
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void colocarNovaPeca(char column, int row, PecaXadrez piece)
	{
		tabuleiro.colocarPeca(piece, new PosicaoXadrez(column, row).toPosicao());
	}
	
	private void ajusteInicial()
	{
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
