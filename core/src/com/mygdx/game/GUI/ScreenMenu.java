package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Player;


public class ScreenMenu extends ScreenState {

    public Stage stage;
    ImageButton bttn_slotmachine;
    ImageButton bttn_blackjack;
    ImageButton bttn_roulette;
    ImageButton bttn_poker;


    public ScreenMenu(ScreenManager sm, Player P) {
        super(sm,P);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        float pad = WIDTH/6;

        Sprite s = new Sprite(new Texture("menu/bttn_slotmachine.png"));
        float scale = (WIDTH/4)/s.getWidth();
        s.setSize(WIDTH/4, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("menu/bttn_d_slotmachine.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());

        bttn_slotmachine = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_slotmachine.setX(WIDTH/2-bttn_slotmachine.getWidth()/2-pad);
        bttn_slotmachine.setY(HEIGHT/2-bttn_slotmachine.getHeight()/2+pad);

        s = new Sprite(new Texture("menu/bttn_blackjack.png"));
        s.setSize(WIDTH/4, scale*s.getHeight());
        sd = new Sprite(new Texture("menu/bttn_d_blackjack.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());
        bttn_blackjack = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_blackjack.setX(WIDTH/2-bttn_blackjack.getWidth()/2+pad);
        bttn_blackjack.setY(HEIGHT/2-bttn_blackjack.getHeight()/2+pad);

        s = new Sprite(new Texture("menu/bttn_roulette.png"));
        s.setSize(WIDTH/4, scale*s.getHeight());
        sd = new Sprite(new Texture("menu/bttn_d_roulette.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());
        bttn_roulette = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_roulette.setX(WIDTH/2-bttn_roulette.getWidth()/2+pad);
        bttn_roulette.setY(HEIGHT/2-bttn_roulette.getHeight()/2-pad);

        s = new Sprite(new Texture("menu/bttn_poker.png"));
        s.setSize(WIDTH/4, scale*s.getHeight());
        sd = new Sprite(new Texture("menu/bttn_d_poker.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());
        bttn_poker = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_poker.setX(WIDTH/2-bttn_poker.getWidth()/2-pad);
        bttn_poker.setY(HEIGHT/2-bttn_poker.getHeight()/2-pad);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);
        stage.addActor(bttn_slotmachine);
        stage.addActor(bttn_blackjack);
        stage.addActor(bttn_roulette);
        stage.addActor(bttn_poker);

        bttn_slotmachine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenSlotMachine(sm, P));
            }
        });

        bttn_blackjack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new StateBJack(sm, P));
            }
        });

        bttn_roulette.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenRoulette(sm, P));
            }
        });

        bttn_poker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // stuff
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
    }



    @Override
    public void update(float dt) {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
