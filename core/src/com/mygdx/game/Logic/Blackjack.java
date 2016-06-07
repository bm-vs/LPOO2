package com.mygdx.game.Logic;

import java.util.Random;
import java.util.Vector;


public class Blackjack
{

    public class jogadasPlayer {
        int valuePlayer = 0;
        int timesPlayer = 0;
        int result;
        Vector<Boolean> ace = new Vector<Boolean>();
        Vector<Card> cartasJoga = new Vector<Card>();

        public Vector<Card> getcartasJogada() {
            return cartasJoga;
        }
        public int getValuePlayer() {
            return valuePlayer;
        }
        public boolean aceExists() {
            for (int i = 0; i < ace.size(); i++) {
                if (ace.get(i)) {
                    return true;
                }
            }

            return false;
        }

    }

    /**
     * Private attribute which stores the bet of the player
     */
    private int aposta;

    /**
     * Private attibute which stores the cards of the game
     */

    public Vector<Card> cartas;
    /**
     * Private attibute which stores the information each player
     */
    private Vector<jogadasPlayer> players;
    /**
     * Private attibute the player of the game
     */
    private Player P;
    /**
     * Public attibute which stores the result of the game
     */
    public  Boolean WIN = false;
    /**
     * Public attibute which stores the result of the game
     */
    public  Boolean LOSE = false;
    /**
     * Public attibute which stores the result of the game
     */
    public Boolean DRAW = false;
    /**
     * Private attribute which stores the index of the dealer in the vector players
     */
    private static final int DEALER = 0;
    /**
     * Private attribute which stores the index of the player in the vector players
     */
    private static final int PLAYER = 1;

    /**
     * Constructor of the class that initialize the private attribute.
     *
     * @param aposta
     * @param P
     */
    public Blackjack(int aposta, Player P) {
        this.P = P;
        this.aposta = aposta;

        cartas = new Vector<Card>(52);

        createNaipe("C");
        createNaipe("E");
        createNaipe("O");
        createNaipe("P");



        players = new Vector<jogadasPlayer>(2);
        players.add(new jogadasPlayer());
        players.add(new jogadasPlayer());

    }

    /**
     * Creates all cards of the suit and places them in vector card
     *
     * @param naipe
     */
    public void createNaipe(String naipe)
    {
        int i=0;
        while (i < 10) {
            i++;
            cartas.add(new Card(naipe,i ,i ));
        }

        while (i < 13) {
            i++;
            cartas.add(new Card(naipe, 10, i));
        }
    }

    /**
     * give the card a player
     *
     * @param Id index of the player
     */
    public Card giveCard(int Id) // Id=0 isDealer
    {
        Card carta;

        Random r = new Random();
        int i = r.nextInt(cartas.size());

        carta = cartas.get(i).clone();
        cartas.remove(i);

        players.get(Id).valuePlayer += carta.getValor();
        players.get(Id).timesPlayer++;


        if (carta.getValor() == 1) {
            if (players.get(Id).valuePlayer + 10 < 22) {
                players.get(Id).valuePlayer += 10;
                players.get(Id).ace.add(true);
            }
            else {
                players.get(Id).ace.add(false);
            }
        }
        else {
            players.get(Id).ace.add(false);
        }


        if (players.get(Id).valuePlayer > 21) {
            for (int it = 0; it < players.get(Id).cartasJoga.size(); it++) {
                if (players.get(Id).ace.get(it)) {
                    players.get(Id).valuePlayer -= 10;
                    players.get(Id).ace.set(it, false);

                    break;
                }
            }
        }

        players.get(Id).cartasJoga.add(carta);
        return carta;
    }

    /**
     * update the variables of the result the game
     */
    public void win() {
        int valueDealer = players.get(DEALER).valuePlayer;

        int value = players.get(PLAYER).valuePlayer;
        if (valueDealer > 21) {
            WIN = true;
            P.setMoney(P.getMoney() + 2 * aposta);
        }
        else if (value > valueDealer && value < 22) {
            if (value == 21 && players.get(1).timesPlayer == 2 && players.get(1).aceExists())
                P.setMoney(P.getMoney() + 2.5 * aposta);
            else
                P.setMoney(P.getMoney() + 2 * aposta);
            WIN = true;
        }
        else if (value == valueDealer) {
            P.setMoney(P.getMoney() + aposta);
            DRAW = true;

        }
        else
            LOSE = true;

    }

    /**
     * ends the dealer´s play
     */
    public void stand() {

        Card card;
        while (players.get(DEALER).getValuePlayer() < 17) {
            card = giveCard(DEALER);
        }

    }

    /**
     * Returns the players of the game
     *
     * @return
     */
    public Vector<jogadasPlayer> getPlayers() {
        return players;
    }


}
