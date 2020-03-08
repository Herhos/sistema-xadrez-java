package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleirodejogo.Peca;
import tabuleirodejogo.Posicao;
import tabuleirodejogo.Tabuleiro;
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
	
	public boolean getXeque()
	{
		return xeque;
	}
	
	public boolean getXequeMate()
	{
		return xequeMate;
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
			throw new ExcecaoXadrez("Voc� n�o pode se colocar em Xeque!");
		}
		
		xeque = (testaXeque(adversario(jogadorAtual))) ? true : false;
		
		if (testaXequeMate(adversario(jogadorAtual)))
		{
			xequeMate = true;
		}
		else
		{
			proximaRodada();
		}		
		
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
		throw new IllegalStateException("N�o existe o Rei " + color + "no tabuleiro!");
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
			// 1�) Pegando os movimentos poss�veis da pe�a "p".
			boolean [][] matriz = p.movimentosPossiveis();
			// Percorrendo a matriz: linhas e colunas.
			for (int linha = 0; linha < tabuleiro.getLinhas(); linha++)
			{
				for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++)
				{
					// Testando se cada elemento da matriz � um movimento poss�vel.
					if (matriz[linha][coluna])
					{
						// Testando se o movimento poss�vel tira do xeque.
						
						// Fez-se um downcasting de "p" porque o atributo "posicao" de peca � protected. 
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(linha, coluna);
						Peca pecaCapturada = movaPeca(origem, destino);
						
						// Testanto agora se o Rei da minha cor ainda est� em xeque
						boolean testeAgora = testaXeque(color);
						desfazMovimento(origem, destino, pecaCapturada);
						
						// Se n�o estava em xeque, esse movimento tirou o Rei do xeque.
						if (!testeAgora)
						{
							return false; // Retorna false porque n�o est� em xeque-mate.
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
		colocarNovaPeca('h', 7, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeca('b', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 8, new Rei(tabuleiro, Cor.PRETO));		
	}
}
