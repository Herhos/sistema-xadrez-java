package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		while (true)
		{
			Interface.imprimeTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Posi��o de origem: ");
			PosicaoXadrez origem = Interface.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Posi��o de destino: ");
			PosicaoXadrez destino = Interface.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partida.executaMovimentoXadrez(origem, destino);
		}		
	}
}
