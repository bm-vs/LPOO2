package com.mygdx.game.Logic;

import java.util.Random;
import java.util.Vector;


public class Blackjack {

    public class gamesPlayer {

        int valuePlayer = 0;
        int timesPlayer = 0;
        boolean asPlayer = false;
        int result;
        Vector<Card> cardsPlayer = new Vector<Card>();

        public Vector<Card> getCardsPlayer() {
            return cardsPlayer;
        }

        public int getValuePlayer() {
            return valuePlayer;
        }

    }

    /**
     * Private attribute which stores the bet of the player
     */
    private int bet;

    /**
     * Private attibute which stores the cards of the game
     */
    public Vector<Card> cards;

    /**
     * Private attibute which stores the information each player
     */
    private Vector<gamesPlayer> players;

    /**
     * Private attibute the player of the game
     */
    private Player P;

    /**
     * Public attibute which stores the result of the game
     */
    public Boolean WIN = false;

    /**
     * Public attibute which stores the result of the game
     */
    public Boolean LOSE = false;

    /**
     * Public attibute which stores the result of the game
     */
    public Boolean DRAW = false;

    /**
     * Private attribute which stores the index of the dealer in the vector players
     */
    private int DEALER = 0;

    /**
     * Private attribute which stores the index of the player in the vector players
     */
    private int PLAYER = 1;


    /**
     * Constructor of the class that initialize the private attribute.
     *
     * @param bet
     * @param P
     */
    public Blackjack(int bet, Player P) {
        this.P = P;
        this.bet = bet;

        cards = new Vector<Card>(52);

        createSuit("C");
        createSuit("E");
        createSuit("O");
        createSuit("P");


        players = new Vector<gamesPlayer>(2);
        players.add(new gamesPlayer());
        players.add(new gamesPlayer());

    }

    /**
     * Creates all cards of the suit and places them in vector card
     *
     * @param suit
     */
    public void createSuit(String suit) {
        int i = 0;
        while (i < 10) {
            i++;
            cards.add(new Card(suit, i, i));
        }

        while (i < 13) {
            i++;
            cards.add(new Card(suit, 10, i));
        }
    }

    /**
     * give the card a player
     *
     * @param Id index of the player
     */
    public void giveCard(int Id) // Id = 0 isDealer
    {
        Card card;

        Random r = new Random();
        int i = r.nextInt(cards.size());

        card = cards.get(i).clone();
        cards.remove(i);

        players.get(Id).valuePlayer += card.getValue();
        players.get(Id).timesPlayer++;


        if (card.getValue() == 1)
            players.get(Id).asPlayer = true;

        if (players.get(Id).asPlayer && players.get(Id).timesPlayer <= 2
                && players.get(Id).valuePlayer + 10 < 22)
            players.get(Id).valuePlayer += 10;

        players.get(Id).cardsPlayer.add(card);

    }

    /**
     * update the variables of the result the game
     */
    public void win() {
        int valueDealer = players.get(DEALER).valuePlayer;

        int value = players.get(PLAYER).valuePlayer;
        if (valueDealer > 21) {
            WIN = true;
            P.setMoney(P.getMoney() + 2 * bet);
        } else if (value > valueDealer && value < 22) {
            if (value == 21 && players.get(1).timesPlayer == 2 && players.get(1).asPlayer)
                P.setMoney(P.getMoney() + 2.5 * bet);
            else
                P.setMoney(P.getMoney() + 2 * bet);
            WIN = true;
        } else if (value == valueDealer) {
            P.setMoney(P.getMoney() + bet);
            DRAW = true;

        } else
            LOSE = true;

    }

    /**
     * ends the dealer´s play
     */
    public void stand() {

        while (players.get(DEALER).getValuePlayer() < 17) {
            giveCard(DEALER);
        }

    }

    /**
     * Returns the players of the game
     *
     * @return
     */
    public Vector<gamesPlayer> getPlayers() {
        return players;
    }


}
