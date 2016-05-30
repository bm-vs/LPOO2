package logic;

import java.util.Random;
import java.util.Vector;

import javax.print.DocFlavor.STRING;

public class Poker {
	
	double maxBet;
	double totalBet;
	Vector <Player> players;
	Vector<Carta> cards;
	Vector<Carta> gameCards= new Vector<Carta>(5);
	
	public Poker(int numPlayer)
	{
		players = new Vector <Player>(numPlayer);
	}
	
	public void foldPlayer(int player)
	{
		players.remove(player);
	}
	
	public void play()
	{
		
	}
	public void newCardGame()
	{
		
		Random r = new Random();
		int i = r.nextInt(cards.size());		
		Carta carta;
	
		carta = cards.get(i);
		cards.remove(i);
		gameCards.add(carta);	
		
	}
}