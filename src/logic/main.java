package logic;

public class main {

	public static void conta(Integer[] a) {
		for (Integer e : a) {
			if (e == null)
				System.out.println("este");
		}
	}

	public static void main(String[] args) {

		SlotMachine S = new SlotMachine();
		int valor = 0;
		int este;
		int i = 0;

		/*
		 * while(i < 100) { este = S.play(); valor +=este; i++; }
		 * 
		 * System.out.println("valor:" + valor);
		 */

		while (i < 100) {
			Backjack B = new Backjack(1,2);

//			for (; i < B.cartas.size(); i++) {
//				// System.out.println(B.cartas.get(i).getCarta());
//
//			}
			System.out.println("player " + B.giveCard(1).getCarta());
			System.out.println("player " + B.giveCard(1).getCarta());
			System.out.println("dealer " + B.giveCard(0).getCarta());
			System.out.println("dealer " + B.giveCard(0).getCarta());
		

			B.stand().size();
			
			System.out.println("player " + B.players.get(1).valuePlayer);
			System.out.println("Dealer " + B.players.get(0).valuePlayer);

			
			/*if (B.win() == 0)
				System.out.println("empate");
			if (B.win() == 1)
				System.out.println("ganhou");
			if (B.win() == -1)
				System.out.println("perdeu");
*/
		
			
			System.out.println("");
			i++;
		}

	}

}
