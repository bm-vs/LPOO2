package com.mygdx.game.Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Player {

    /**
     * Private attibute which stores the money of the player
     */
    private double money;
    private double earnings_slot;
    private double earnings_roulette;
    private double earnings_blackjack;

    /**
     * Constructor of the class that initialize the private attribute.
     */
    public Player() {
        readFile();
    }

    /**
     * Change the money of the player
     *
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
        writeFile();
    }

    /**
     * Change the earnings in slot machine
     *
     * @param money
     */
    public void addEarningsSlot(double money) {
        earnings_slot += money;
        writeFile();
    }

    /**
     * Change the earnings in roulette
     *
     * @param money
     */
    public void addEarningsRoulette(double money) {
        earnings_roulette += money;
        writeFile();
    }

    /**
     * Change the earnings in blackjack
     *
     * @param money
     */
    public void addEarningsBlackjack(double money) {
        earnings_blackjack += money;
        writeFile();
    }


    /**
     * Return the money of the player
     *
     * @return money
     */
    public double getMoney() {
        return money;
    }


    /**
     * Return the earnings in slot machine
     *
     * @return earnings_slot
     */
    public double getEarningsSlot() {
        return earnings_slot;
    }

    /**
     * Return the earnings in roulette
     *
     * @return earnings_roulette
     */
    public double getEarningsRoulette() {
        return earnings_roulette;
    }

    /**
     * Return the earnings in blackjack
     *
     * @return earnings_blackjack
     */
    public double getEarningsBlackjack() {
        return earnings_blackjack;
    }


    /**
     * Reads the players stats from file
     */
    public void readFile() {
        FileHandle stats = Gdx.files.local("player.txt");

        try {
            if (stats.exists()) {
                String loaded = stats.readString();
                String[] s = loaded.split("=");
                money = Double.parseDouble(s[0]);
                earnings_slot = Double.parseDouble(s[1]);
                earnings_roulette = Double.parseDouble(s[2]);
                earnings_blackjack = Double.parseDouble(s[3]);
            }
            else {
                money = 100;
                earnings_slot = 0;
                earnings_roulette = 0;
                earnings_blackjack = 0;
                writeFile();
            }
        }
        catch (NumberFormatException e) {
            money = 100;
            earnings_slot = 0;
            earnings_roulette = 0;
            earnings_blackjack = 0;
            writeFile();
        }
    }

    /**
     * Writes the players stats to file
     */
    public void writeFile() {
        try {
            FileHandle stats = Gdx.files.local("player.txt");

            if (!stats.exists()) {
                stats.file().createNewFile();
            }

            String s = String.format("%f=%f=%f=%f", money, earnings_slot, earnings_roulette, earnings_blackjack);
            stats.writeString(s, false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}