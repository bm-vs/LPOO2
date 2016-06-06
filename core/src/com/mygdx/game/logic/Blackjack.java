package com.mygdx.game.Logic;

import java.util.Random;
import java.util.Vector;


public class Blackjack
{

    public class jogadasPlayer {

        int valuePlayer = 0;
        int timesPlayer = 0;
        boolean asPlayer = false;
        int result;
        Vector<Carta> cartasJoga = new Vector<Carta>();

        public Vector<Carta> getcartasJogada() {
            return cartasJoga;
        }

        public int getValuePlayer() {
            return valuePlayer;
        }

    }

    private int aposta;
    public Vector<Carta> cartas;
    private Vector<jogadasPlayer> players;
    private Player P;
    public  Boolean WIN = false;
    public  Boolean LOSE = false;
    public Boolean DRAW = false;
    private static final int DEALER = 0;
    private static final int PLAYER = 1;


    public Blackjack(int aposta, Player P) {
        this.P = P;
        this.aposta = aposta;

        cartas = new Vector<Carta>(52);

        createNaipe("C");
        createNaipe("E");
        createNaipe("O");
        createNaipe("P");



        players = new Vector<jogadasPlayer>(2);
        players.add(new jogadasPlayer());
        players.add(new jogadasPlayer());

    }
    public void createNaipe(String naipe)
    {
        int i=0;
        while (i < 10) {
            i++;
            cartas.add(new Carta(naipe,i ,i ));
             }

        while (i < 13) {
            i++;
            cartas.add(new Carta(naipe, 10, i));
        }
    }

    public Carta giveCard(int Id) // Id=0 isDealer
    {
        Carta carta;

        Random r = new Random();
        int i = r.nextInt(cartas.size());

        carta = cartas.get(i).clone();
        cartas.remove(i);

        players.get(Id).valuePlayer += carta.getValor();
        players.get(Id).timesPlayer++;


        if (carta.getValor() == 1)
            players.get(Id).asPlayer = true;

        if (players.get(Id).asPlayer && players.get(Id).timesPlayer <= 2
                && players.get(Id).valuePlayer + 10 < 22)
            players.get(Id).valuePlayer += 10;

        players.get(Id).cartasJoga.add(carta);
        return carta;
    }

    public void win() {
        int valueDealer = players.get(DEALER).valuePlayer;

        int value = players.get(PLAYER).valuePlayer;
        if (valueDealer > 21) {
            WIN = true;
            P.setMoney(P.getMoney() + 2 * aposta);
        }
        else if (value > valueDealer && value < 22) {
            if (value == 21 && players.get(1).timesPlayer == 2 && players.get(1).asPlayer)
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

    public void stand() {

        Carta card;
        while (players.get(DEALER).getValuePlayer() < 17) {
            card = giveCard(DEALER);
        }

    }

    public Vector<jogadasPlayer> getPlayers() {
        return players;
    }


}
