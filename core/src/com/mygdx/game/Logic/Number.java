package com.mygdx.game.Logic;

public class Number {

    /**
     * Private attribute number
     */
    private int number;

    /**
     * Private attibute which stores the color of the number
     */
    private String color;

    /**
     * Constructor of the class that inicialize all the private attibutes.
     *
     * @param n
     * @param c
     */
    public Number(int n, String c) {
        number = n;
        color = c;
    }

    /**
     * @return number
     */
    public int getNumber() {
        return number;
    }


    /**
     * Return the color of the number
     *
     * @return color
     */
    public String getColor() {
        return color;
    }
}