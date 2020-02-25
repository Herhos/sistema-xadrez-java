package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		while (true)
		{
			try
			{
				Interface.limparTela();
				Interface.imprimeTabuleiro(partida.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = Interface.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				Interface.limparTela();
				Interface.imprimeTabuleiro(partida.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = Interface.lerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partida.executaMovimentoXadrez(origem, destino);
			}
			catch (ExcecaoXadrez e)
			{
				System.out.println(e.getMessage());
				sc.nextLine(); // Apenas para aguardar usuário teclar ENTER
			}
			catch (InputMismatchException e)
			{
				System.out.println(e.getMessage());
				sc.nextLine(); // Apenas para aguardar usuário teclar ENTER
			}
		}
	}
}
