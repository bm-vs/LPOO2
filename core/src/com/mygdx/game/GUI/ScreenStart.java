package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Player;


public class ScreenStart extends com.mygdx.game.GUI.ScreenState {
    public Stage stage;

    public ScreenStart(ScreenManager sm, Player P) {
        super(sm, P);
        create();
    }

    @Override
    public void create() {

        stage = new Stage(new ScreenViewport());

        //===============================================================================================================
        // START BUTTON

        Sprite s = new Sprite(new Texture("start/bttn_start.png"));
        float scale = (WIDTH/4)/s.getWidth();
        s.setSize(WIDTH/4, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("start/bttn_start_d.png"));
        scale = (WIDTH/4)/s.getWidth();
        sd.setSize(WIDTH/4, scale*s.getHeight());

        ImageButton bttn_start = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_start.setTransform(true);
        bttn_start.setX(WIDTH/2-bttn_start.getWidth()/2);
        bttn_start.setY(HEIGHT/2-bttn_start.getHeight()/2 - HEIGHT/8);

        //===============================================================================================================
        // CASINO IMAGE

        Image casino_image = new Image(new Texture("start/img_casino.png"));
        scale = (WIDTH/2)/casino_image.getWidth();
        casino_image.setSize(WIDTH/2, scale*casino_image.getHeight());
        casino_image.setX(WIDTH/2-casino_image.getWidth()/2);
        casino_image.setY(HEIGHT/2-casino_image.getHeight()/2 + HEIGHT/8);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(bttn_start);
        stage.addActor(casino_image);

        bttn_start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenMenu(sm, P));
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        stage.act();
        stage.draw();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
