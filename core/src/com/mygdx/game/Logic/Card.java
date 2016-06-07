package com.mygdx.game.Logic;


public class Card  implements Cloneable{

    private String naipe;
    private int valor;
    private int carta;//valor de 1 a 13 representado as diferentes cartas

    public Card(String naipe, int valor, int carta)
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


    public String toda()
    {
        return naipe+ " " + valor + " " + carta;
    }

    public Card clone()
    {
        return new Card(naipe, valor, carta);
    }



}
