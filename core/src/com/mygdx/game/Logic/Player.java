package com.mygdx.game.Logic;

public class Player {

    /**
     * Private attibute which stores the money of the player
     */
    private double money;

    /**
     * Constructor of the class that initialize the private attribute.
     *
     * @param money
     */
    public Player(double money) {
        this.money = money;
    }

    /**
     * Change the money of the player
     *
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Return the money of the player
     *
     * @return money
     */
    public double getMoney() {
        return money;
    }

}