package com.mygdx.game.logic;

import java.util.Random;
import java.util.Vector;

public class SlotMachine {
    private int v_maximo = 10;
    private int v_medio = 5;
    private int v_minimo = 2;

    private int n1, n2, n3;


    public int play() {
        Random r = new Random();


        n1 = r.nextInt(10);
        n2 = r.nextInt(10);
        n3 = r.nextInt(10);

        System.out.println(n1 + "  " + n2 + "  " +n3);


        if (n1 == n2)
        {
            if(n2 == n3)
            {
                if(n1 ==0 || n1==3 || n1==7)//caso os 3 numeros sejam 3 ou 7 ou 0
                    return v_maximo;
                else
                    return	v_medio;//3 quaisquer numeros iguais
            }
            if(n1==7)//os dois primeiros numeros ser 7
                return v_medio;
            else
                return v_minimo;//os dois primeiros iguais e o ultimo diferente
        }

        if(n2 == n3)//primeiro diferente e dois ultimos iguais
            return v_minimo;

        if(n1==n3)//primeiro e ultimo igual
            return v_minimo;

        return 0;
    }


    public Vector<Integer> getNumbers()
    {
        Vector<Integer> numbers = new Vector<Integer>();
        numbers.add(n1);
        numbers.add(n2);
        numbers.add(n3);


        return numbers;
    }

}