package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public abstract class StateBase {

    protected ScreenManeger sm;
    protected int WIDTH = Gdx.graphics.getWidth();
    protected int HEIGHT = Gdx.graphics.getHeight();


    protected StateBase(ScreenManeger sm) {

        this.sm = sm;
    }

    protected abstract void create();

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

    public static void setSize(int w, int h) {
    }
}



