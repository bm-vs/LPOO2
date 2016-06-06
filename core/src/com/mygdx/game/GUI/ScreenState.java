package com.mygdx.game.GUI;


import com.badlogic.gdx.Gdx;

public abstract class ScreenState {
    public static  final  int WIDTH = Gdx.graphics.getWidth();
    public static  final  int HEIGHT = Gdx.graphics.getHeight();

    protected ScreenManager sm;

    protected ScreenState(ScreenManager sm) {
        this.sm = sm;
    }

    public abstract void create();

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

    public static void setSize(int w, int h) {}
}



