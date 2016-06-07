package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Logic.Player;

public abstract class ScreenState {
    public static  final  int WIDTH = Gdx.graphics.getWidth();
    public static  final  int HEIGHT = Gdx.graphics.getHeight();
    protected ScreenManager sm;
    protected Player P;
    protected int secondMoney = 150;

    protected ScreenState(ScreenManager sm, Player P) {
        this.sm = sm;
        this.P = P;
    }

    public abstract void create();

    //protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

    public static void setSize(int w, int h) {}
}



