package logic;

import java.util.Vector;

public class Player  {

	double money;
	double bet;
	Vector<Carta> cartas = new Vector<Carta>(2);
	
	public Player(double money)
	{
		this.money= money;		
	}
	
	public void addCard(Carta carta)
	{
		cartas.add(carta);
	}
	public void call(double bet)
	{
		double missbet= this.bet-bet;
		money -=missbet;
		this.bet=bet;
		
	}
	
	public void raise(double value)
	{
		money -= value;
		bet += value;
	}
	
	public void bet(double bet)
	{
		money -= bet;
		this.bet +=bet;
	}
	
	

	
	
}