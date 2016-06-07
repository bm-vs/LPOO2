package com.mygdx.game.Test;

import com.mygdx.game.Logic.Blackjack;
import com.mygdx.game.Logic.Card;
import com.mygdx.game.Logic.Player;
import com.mygdx.game.Logic.Roulette;
import com.mygdx.game.Logic.SlotMachine;

import org.junit.Test;
import static org.junit.Assert.*;

public class CasinoTester {


    @Test
    public void testSlotMachine() {
        SlotMachine s = new SlotMachine();
        int valor = 0;
        int i = 0;
        int numberBets = 1000;


        while (i < numberBets) {
            s.roll();
            valor += s.prize();
            i++;
        }

        assertTrue(valor < numberBets);
    }

    @Test
    public void testBlackJack() {

        int bet = 10;
        Player P = new Player();

        Blackjack B = new Blackjack(bet, P);
        B.giveCard(1);
        B.giveCard(1);
        B.stand();
        B.win();

        int pointsDealer = B.getPlayers().get(0).getValuePlayer();
        int pointsPlayer = B.getPlayers().get(1).getValuePlayer();

        if (pointsDealer > pointsPlayer)
            assertTrue(B.LOSE);

        if (pointsDealer == pointsPlayer)
            assertTrue(B.DRAW);


        if (pointsDealer < pointsPlayer)
            assertTrue(B.WIN);

        assertTrue(pointsDealer >= 17);
    }

    @Test
    public void testCardsInBlackjack() {

        int bet = 10;
        Player P = new Player();

        Blackjack B = new Blackjack(bet, P);

        assertTrue(B.cartas.size() == 52);

        Card c = B.cartas.get(0);
        assertEquals(c.getNaipe(), "C");
        assertEquals(c.getcarta(), 1);
    }

    @Test
    public void testRoulette() {

        Roulette r = new Roulette();
        int bet = 10;
        int numberOfBets = 10000;
        int totalBet = numberOfBets * bet;

        int i = 0;
        int number;
        int money = 0;

        Integer[] bet_numbers = {1, 3, 5, 7, 9, 11};


        while (i < numberOfBets) {
            number = r.roll();
            money += r.betReturn(bet, bet_numbers);
            i++;
        }

        assertTrue(money < totalBet);
    }
}


