package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez
{
	// ATRIBUTOS E PROPRIEDADES //
	
	private int rodada;
	private Cor jogadorAtual;	
	private Tabuleiro tabuleiro;
	private boolean xeque; // Propriedades boolean sempre iniciam como false.
	private boolean xequeMate; // Propriedades boolean sempre iniciam como false.
	private PecaXadrez enPassantVulneravel; // Propriedades private sempre iniciam como null.
	
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
	
	public boolean getXequeMate()
	{
		return xequeMate;
	}
	
	public PecaXadrez getEnPassantVulneravel()
	{
		return enPassantVulneravel;
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
		
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
		
		xeque = (testaXeque(adversario(jogadorAtual))) ? true : false;
		
		if (testaXequeMate(adversario(jogadorAtual)))
		{
			xequeMate = true;
		}
		else
		{
			proximaRodada();
		}
		
		// Testar se a peça movida foi e um Peão, e se ele se moveu por duas casas.
		
		if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2
			|| destino.getLinha() == origem.getLinha() + 2))
		{
			enPassantVulneravel = pecaMovida;
		}
		else
		{
			enPassantVulneravel = null;
		}
		
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
	
	// Corresponde ao makeMove.
	private Peca movaPeca(Posicao origem, Posicao destino)
	{
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(origem);
		p.aumentaContaMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (pecaCapturada != null)
		{
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		// Implementando o movimento de Roque pequeno (castling kingside rook).
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.aumentaContaMovimentos();
		}
		
		// Implementando o movimento de Roque grande (castling queenside rook).
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.aumentaContaMovimentos();
		}
		
		// Implementando o movimento de En Passant.
		
		if (p instanceof Peao)
		{
			// Se o meu peão andou na diagonal e não capturou uma peça, então ocorreu en passant.
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null)
			{
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO)
				{
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}
				else
				{
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
	
	// Corresponde ao undoMove.
	private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada)
	{
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(destino);
		p.diminuiContaMovimentos();
		tabuleiro.colocarPeca(p, origem);
		
		if (pecaCapturada != null)
		{
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		// Desfazendo o movimento de Roque pequeno (castling kingside rook).
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.diminuiContaMovimentos();
		}
		
		// Desfazendo o movimento de Roque grande (castling queenside rook).
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.diminuiContaMovimentos();
		}
		
		// Desfazendo o movimento de En Passant.
		
		if (p instanceof Peao)
		{
			// Se o meu peão andou na diagonal e não capturou uma peça, então ocorreu en passant.
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel)
			{
				PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);
				
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO)
				{
					posicaoPeao = new Posicao(3, destino.getColuna());
				}
				else
				{
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.colocarPeca(peao, posicaoPeao);				
			}
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
	
	private boolean testaXequeMate(Cor color)
	{
		if (!testaXeque(color))
		{
			return false;
		}
		
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == color).
				collect(Collectors.toList());
		for (Peca p : lista)
		{
			// 1°) Pegando os movimentos possíveis da peça "p".
			boolean [][] matriz = p.movimentosPossiveis();
			// Percorrendo a matriz: linhas e colunas.
			for (int linha = 0; linha < tabuleiro.getLinhas(); linha++)
			{
				for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++)
				{
					// Testando se cada elemento da matriz é um movimento possível.
					if (matriz[linha][coluna])
					{
						// Testando se o movimento possível tira do xeque.
						
						// Fez-se um downcasting de "p" porque o atributo "posicao" de peca é protected. 
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(linha, coluna);
						Peca pecaCapturada = movaPeca(origem, destino);
						
						// Testanto agora se o Rei da minha cor ainda está em xeque
						boolean testeAgora = testaXeque(color);
						desfazMovimento(origem, destino, pecaCapturada);
						
						// Se não estava em xeque, esse movimento tirou o Rei do xeque.
						if (!testeAgora)
						{
							return false; // Retorna false porque não está em xeque-mate.
						}
					}
				}
			}
		}
		return true;
	}
	
	private void colocarNovaPeca(char column, int row, PecaXadrez piece)
	{
		tabuleiro.colocarPeca(piece, new PosicaoXadrez(column, row).toPosicao());
		pecasNoTabuleiro.add(piece);
	}
	
	private void ajusteInicial()
	{
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
				
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
}
