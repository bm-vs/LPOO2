package com.mygdx.game.Logic;

import java.util.Random;

public class Roulette {
    private Number currentNumber;
    Number[] wheel;

    public Roulette() {
        currentNumber = new Number(-1,"");
        wheel = new Number[37];

        // Green
        wheel[0] = new Number(0,  "green");
        // Red
        wheel[23] = new Number(1, "red"); wheel[35] = new Number(3, "red"); wheel[19] = new Number(5, "red"); wheel[31] = new Number(7, "red"); wheel[27] = new Number(9, "red"); wheel[33] = new Number(12, "red"); wheel[25] = new Number(14, "red"); wheel[21] = new Number(16, "red"); wheel[29] = new Number(18, "red"); wheel[3] = new Number(19, "red"); wheel[5] = new Number(21, "red"); wheel[17] = new Number(23, "red"); wheel[7] = new Number(25, "red"); wheel[11] = new Number(27, "red"); wheel[15] = new Number(30, "red"); wheel[1] = new Number(32, "red"); wheel[9] = new Number(34, "red"); wheel[13] = new Number(36, "red");
        // Black
        wheel[6] = new Number(2, "black"); wheel[4] = new Number(4, "black"); wheel[10] = new Number(6, "black"); wheel[16] = new Number(8, "black"); wheel[18] = new Number(10,  "black"); wheel[14] = new Number(11, "black"); wheel[12] = new Number(13, "black"); wheel[2] = new Number(15, "black"); wheel[8] = new Number(17, "black"); wheel[24] = new Number(20, "black"); wheel[28] = new Number(22, "black"); wheel[20] = new Number(24, "black"); wheel[36] = new Number(26, "black"); wheel[32] = new Number(28, "black"); wheel[30] = new Number(29, "black"); wheel[26] = new Number(31, "black"); wheel[22] = new Number(33, "black"); wheel[34] = new Number(35, "black");
    }

    public Number getCurrentNumber() {
        return currentNumber;
    }

    public int roll() {
        Random r = new Random();
        int i = r.nextInt(37);
        currentNumber = wheel[i];

        return i;
    }

    public int betReturn(int bet_value, Integer[] bet_numbers) {
        for (int i = 0; i < bet_numbers.length; i++) {
            if (bet_numbers[i] == currentNumber.getNumber()) {
                return bet_value * (wheel.length-1)/bet_numbers.length;
            }
        }

        return 0;
    }
}