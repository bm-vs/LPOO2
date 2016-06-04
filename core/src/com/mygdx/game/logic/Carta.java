package com.mygdx.game.logic;


public class Carta  implements Cloneable{

    private String naipe;
    private int valor;
    private int carta;//valor de 1 a 13 representado as diferentes cartas

    public Carta(String naipe, int valor, int carta)
    {
        this.naipe = naipe;
        this.valor = valor;
        this.carta= carta;
    }

    public String getNaipe()
    {
        return naipe;
    }

    public int getcarta()
    {
        return carta;
    }
    public int getValor()
    {
        return valor;
    }


   /* public String getCarta()
    {
        return naipe+ " " + valor + " " + carta;
    }*/

    public Carta clone()
    {
        return new Carta(naipe, valor, carta);
    }



}
