package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez
{
	// ATRIBUTOS //
	
	private int rodada;
	private Cor jogadorAtual;	
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	// CONSTRUTOR PADR�O //
	
	public PartidaXadrez()
	{
		tabuleiro = new Tabuleiro(8, 8);
		rodada = 1;
		jogadorAtual = Cor.BRANCO;
		ajusteInicial();
	}
	
	// GETTERS //
	
	public int getRodada()
	{
		return rodada;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	// M�TODOS ESPEC�FICOS //

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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez sourcePosition)
	{
		Posicao position = sourcePosition.toPosicao();
		validaPosicaoOrigem(position);
		return tabuleiro.peca(position).movimentosPossiveis();
	}
	
	public PecaXadrez executaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino)
	{
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem, destino);
		Peca pecaCapturada = movaPeca(origem, destino);
		proximaRodada();
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao position)
	{
		if (!tabuleiro.existePeca(position))
		{
			throw new ExcecaoXadrez("N�o existe uma pe�a na posi��o de origem. Tecle ENTER para retornar!");
		}
		
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(position)).getCor())
		{
			throw new ExcecaoXadrez("A pe�a escolhida n�o � sua!");
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
	
	private void proximaRodada()
	{
		rodada++;
		// Essa express�o tern�ria abaixo substitui um IF.
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Peca movaPeca(Posicao origem, Posicao destino)
	{
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (pecaCapturada != null)
		{
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	
	private void colocarNovaPeca(char column, int row, PecaXadrez piece)
	{
		tabuleiro.colocarPeca(piece, new PosicaoXadrez(column, row).toPosicao());
		pecasNoTabuleiro.add(piece);
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
