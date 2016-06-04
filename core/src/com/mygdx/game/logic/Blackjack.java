package com.mygdx.game.logic;

import com.mygdx.game.logic.Carta;
import com.mygdx.game.logic.Player;

import java.util.Random;
import java.util.Vector;

public class Blackjack {

    public class jogadasPlayer {

        int valuePlayer = 0;
        int timesPlayer = 0;
        boolean asPlayer = false;
        int result;
        Vector<Carta> cartasJoga = new Vector<Carta>();

        public   Vector<Carta> getcartasJogada()
        {
            return cartasJoga;
        }
    }

    private int aposta;
    private Vector<Carta> cartas;
    private Vector<jogadasPlayer> players;
    private Player P;

    public Blackjack(int aposta,  Player P) {
        this.P=P;

        this.aposta = aposta;
        int i = 0;
        cartas = new Vector<Carta>(52);
        int j=1;
        while (i < 10)
        {

            cartas.add(i, new Carta("C", j,j));
            j++;
            i++;

        }

        while (i < 13) {
            cartas.add(i, new Carta("C", 10, i));
            i++;
        }
        j=1;
        while (i < 23)
            cartas.add(i, new Carta("E", ++i - 13, j++));

        j=10;
        while (i < 26) {
            cartas.add(i, new Carta("E", 10,j++));
            i++;
        }

        j=1;
        while (i < 36)
            cartas.add(i, new Carta("O", ++i - 26,j++));

        j=10;
        while (i < 39) {
            cartas.add(i, new Carta("O", 10,j++));
            i++;
        }
        j=1;
        while (i < 49)
            cartas.add(i, new Carta("P", ++i - 39,j++));

        j=10;
        while (i < 52) {
            cartas.add(i, new Carta("P", 10,j++));
            i++;
        }

        players = new Vector<jogadasPlayer>(3);
        players.add(0,new jogadasPlayer());
        players.add(1,new jogadasPlayer());

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
        int valueDealer = players.get(0).valuePlayer;

        int value = players.get(1).valuePlayer;
        if (valueDealer > 21)
            P.setMoney(P.getMoney() + 2 * aposta);

        if (value > valueDealer && value < 22)
            P.setMoney(P.getMoney() + 2 * aposta);

        if (value == valueDealer)
            P.setMoney(P.getMoney() + aposta);

    }

    public Vector<Carta> stand() {

        Vector<Carta> allCard = new Vector<Carta>();
        Carta card;
        while (players.get(0).valuePlayer < 17) {
            card = giveCard(0);
            players.get(0).cartasJoga.add(card);
            allCard.add(card);
        }
        return allCard;

    }

    public Vector<jogadasPlayer> getPlayers()
    {
        return players;
    }




}
