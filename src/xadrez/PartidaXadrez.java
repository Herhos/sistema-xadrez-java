package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	private boolean xeque; // Propriedades boolean sempre iniciam como false.
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	// CONSTRUTOR PADRÃO //
	
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
	
	public boolean getXeque()
	{
		return xeque;
	}
	
	// MÉTODOS ESPECÍFICOS //

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
	
	// Corresponde ao performChessMove
	public PecaXadrez executaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino)
	{
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem, destino);
		Peca pecaCapturada = movaPeca(origem, destino);
		
		if (testaXeque(jogadorAtual))
		{
			desfazMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em Xeque!");
		}
		
		xeque = (testaXeque(adversario(jogadorAtual))) ? true : false;
		
		proximaRodada();
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao position)
	{
		if (!tabuleiro.existePeca(position))
		{
			throw new ExcecaoXadrez("Não existe uma peça na posição de origem. Tecle ENTER para retornar!");
		}
		
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(position)).getCor())
		{
			throw new ExcecaoXadrez("A peça escolhida não é sua!");
		}
		
		// Se a peça estiver presa em determinada posição do tabuleiro, lançar uma exceção.
		if (!tabuleiro.peca(position).pecaPresa())
		{
			throw new ExcecaoXadrez("Não existem movimentos possíveis disponíveis!");
		}
	}
	
	private void validaPosicaoDestino(Posicao source, Posicao target)
	{
		if (!tabuleiro.peca(source).seMove(target))
		{
			throw new ExcecaoXadrez("A peça escolhida não pode ser movida"
				+ " para a posição de destino!");
		}
	}
	
	private void proximaRodada()
	{
		rodada++;
		// Essa expressão ternária abaixo substitui um IF.
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
	
	private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada)
	{
		Peca p = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, origem);
		
		if (pecaCapturada != null)
		{
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private Cor adversario(Cor color)
	{
		return (color == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez corDoRei(Cor color)
	{
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == color).
			collect(Collectors.toList());
		for (Peca p : lista)
		{
			if (p instanceof Rei)
			{
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o Rei " + color + "no tabuleiro!");
	}
	
	private boolean testaXeque(Cor color)
	{
		Posicao posicaoDoRei = corDoRei(color).getPosicaoXadrez().toPosicao();
		List<Peca> pecasDoAdversario = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == adversario(color)).
				collect(Collectors.toList());
		for (Peca p : pecasDoAdversario)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()])
			{
				return true;
			}
		}
		return false;
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
