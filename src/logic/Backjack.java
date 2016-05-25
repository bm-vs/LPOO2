package logic;

import java.util.Random;
import java.util.Vector;

public class Backjack {

	public class Player {

		int valuePlayer = 0;
		int timesPlayer = 0;
		boolean asPlayer = false;
		int money = 0;
		int result;
	}

	int aposta;
	Vector<Carta> cartas;
	Vector<Player> players;

	public Backjack(int aposta, int numberPlayer) {

		this.aposta = aposta;
		int i = 0;
		cartas = new Vector<Carta>(52);

		while (i < 10)
			cartas.add(i, new Carta("Copas", ++i));

		while (i < 13) {
			cartas.add(i, new Carta("Copas", 10));
			i++;
		}

		while (i < 23)
			cartas.add(i, new Carta("Espadas", ++i - 13));

		while (i < 26) {
			cartas.add(i, new Carta("Espadas", 10));
			i++;
		}

		while (i < 36)
			cartas.add(i, new Carta("Ouros", ++i - 26));

		while (i < 39) {
			cartas.add(i, new Carta("Ouros", 10));
			i++;
		}

		while (i < 49)
			cartas.add(i, new Carta("Paus", ++i - 39));

		while (i < 52) {
			cartas.add(i, new Carta("Paus", 10));
			i++;
		}

		players = new Vector<Player>(numberPlayer);

		for (i = 0; i < numberPlayer; i++)
			players.add(new Player());

	}

	public Carta giveCard(int Id) // Id=0 isDealer
	{
		Carta carta;

		Random r = new Random();
		int i = r.nextInt(cartas.size());

		carta = cartas.get(i).clone();
		cartas.remove(i);

		players.get(Id).valuePlayer += carta.valor;
		players.get(Id).timesPlayer++;

		if (carta.valor == 1)
			players.get(Id).asPlayer = true;

		if (players.get(Id).asPlayer && players.get(Id).timesPlayer <= 2
				&& players.get(Id).valuePlayer + 10 < 22)
			players.get(Id).valuePlayer += 10;

		return carta;
	}

	public void win() {
		int valueDealer = players.get(0).valuePlayer;

		for (int i = 1; i < players.size(); i++) {
			int value = players.get(i).valuePlayer;
			if (value > 21)
				players.get(i).result = -1;

			if (valueDealer > 21)
				players.get(i).result = 1;

			if (value > valueDealer)
				players.get(i).result = 1;

			if (value == valueDealer)
				players.get(i).result = 0;
			else
				players.get(i).result = -1;
		}
	}

	public Vector<Carta> stand() {
		Vector<Carta> allCard = new Vector<Carta>();
		Carta card;
		while (players.get(0).valuePlayer < 17) {
			card = giveCard(0);
			allCard.add(card);
		}
		return allCard;

	}

	public void gain(int Id) {
		if (players.get(Id).timesPlayer == 2)
			players.get(Id).money += 2.5 * aposta;

		else
			players.get(Id).money += 2 * aposta;
	}

}
