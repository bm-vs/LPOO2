package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.GUI.ScreenManager;
import com.mygdx.game.GUI.ScreenMenu;

public class Casino extends ApplicationAdapter
{
    private ScreenManager state;
    public static  final  int WIDTH = 500;
    public static  final  int HEIGHT = 600;
    public static  final  String TITTLE = "Casino";



    @Override
    public void create () {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        state = new ScreenManager();
        state.add(new ScreenMenu(state));
    }

    @Override
 public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        state.update(Gdx.graphics.getDeltaTime());
        state.render();

 }
 }