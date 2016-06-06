package com.mygdx.game.Logic;

public class Player {

    private double money;

    public Player(double money ) {
        this.money = money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney()
    {
        return money;
    }

}
