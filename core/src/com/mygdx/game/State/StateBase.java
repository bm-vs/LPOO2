package com.mygdx.game.State;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Acer-PC on 02/06/2016.
 */
public abstract class StateBase {

    protected ScreenManeger sm;
    protected Texture background;

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



