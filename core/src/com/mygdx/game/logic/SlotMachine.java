package com.mygdx.game.Logic;

import java.util.Random;

public class SlotMachine {

    /**
     * Private attibute the first number
     */
    private int n1;

    /**
     * Private attibute the second number
     */
    private int n2;

    /**
     * Private attribute the laste number
     */
    private int n3;

    /**
     * Constructor of the class with no parameters.
     */
    public SlotMachine() {
    }

    /**
     * Returns the first number
     *
     * @return n1
     */
    public int getN1() {
        return n1;
    }

    /**
     * Returns the second number
     *
     * @return n2
     */
    public int getN2() {
        return n2;
    }


    /**
     * Returns the last number
     *
     * @return n3
     */
    public int getN3() {
        return n3;
    }

    /**
     * Inicialize the three numbers
     */
    public void roll() {
        Random r = new Random();
        n1 = r.nextInt(10);
        n2 = r.nextInt(10);
        n3 = r.nextInt(10);
    }

    /**
     * @return the gain value in the game
     */
    public int prize() {
        int v_maximo = 10;
        int v_medio = 5;
        int v_minimo = 2;

        if (n1 == n2) {
            if (n2 == n3) {
                if (n1 == 0 || n1 == 3 || n1 == 7)//the three  equals numbers and are 0 or 3 or 7
                    return v_maximo;
                else
                    return v_medio;//the three equals numbers
            }
            if (n1 == 7)
                return v_medio;
            else
                return v_minimo;//the first equals and the last number different
        }

        if (n2 == n3)//last equal numbers
            return v_minimo;

        if (n1 == n3)//the first and the last number equal
            return v_minimo;

        return 0;
    }
}