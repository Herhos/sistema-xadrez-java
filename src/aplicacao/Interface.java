package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Interface
{
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc)
	{
		try
		{
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e)
		{
			throw new InputMismatchException("Erro ao ler PosicaoXadrez: valores v�lidos "
				+ "de a1 � h8! Tecle ENTER para retornar!");
		}		
	}
	
	public static void imprimePartida (PartidaXadrez chessMatch, List<PecaXadrez> capturadas)
	{
		imprimeTabuleiro(chessMatch.getPecas());
		System.out.println();
		imprimePecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Rodada: " + chessMatch.getRodada());
		if (!chessMatch.getXequeMate())
		{
			System.out.println("Aguardando movimento do jogador: " + chessMatch.getJogadorAtual());
			if (chessMatch.getXeque())
			{
				System.out.println("XEQUE!");
			}
		}
		else
		{
			System.out.println("XEQUE-MATE!");
			System.out.println("Vencedor: " + chessMatch.getJogadorAtual());
		}
	}
	
	public static void imprimeTabuleiro(PecaXadrez[][] pieces)
	{
		for(int linha = 0; linha < pieces.length; linha++)
		{
			System.out.print((8 - linha + " "));
			for (int coluna = 0; coluna < pieces.length; coluna++)
			{
				imprimePeca(pieces[linha][coluna], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	// M�todo imprimeTabuleiro com sobrecarga
	public static void imprimeTabuleiro(PecaXadrez[][] pieces, boolean[][] movimentosPossiveis)
	{
		for(int linha = 0; linha < pieces.length; linha++)
		{
			System.out.print((8 - linha + " "));
			for (int coluna = 0; coluna < pieces.length; coluna++)
			{
				imprimePeca(pieces[linha][coluna], movimentosPossiveis[linha][coluna]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void imprimePeca(PecaXadrez piece, boolean corFundo)
	{
		if (corFundo)
		{
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if(piece == null)
		{
			System.out.print("-" + ANSI_RESET);
		}
		else
		{
			if (piece.getCor() == Cor.BRANCO)
			{
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else
            {
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
		}
		System.out.print(" ");
	}
	
	private static void imprimePecasCapturadas(List<PecaXadrez> capturada)
	{
		// Filtrando uma lista com uma express�o l�mbda.
		List<PecaXadrez> brancas = capturada.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> pretas = capturada.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		
		System.out.println("Pe�as capturadas:");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.println("Pe�as capturadas:");
		System.out.print("Pretas: ");
		System.out.print(ANSI_RED);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);		
	}
}
