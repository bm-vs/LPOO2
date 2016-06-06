package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class ScreenMenu extends ScreenState {

    public Stage stage;
    ImageButton bttn_slotmachine;
    ImageButton bttn_blackjack;
    ImageButton bttn_roulette;
    ImageButton bttn_poker;

    public ScreenMenu(ScreenManager sm) {
        super(sm);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());

        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();
        float pad = screen_width/6;

        Sprite s = new Sprite(new Texture("menu/bttn_slotmachine.png"));
        float scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());

        bttn_slotmachine = new ImageButton(new SpriteDrawable(s));
        bttn_slotmachine.setX(screen_width/2-bttn_slotmachine.getWidth()/2-pad);
        bttn_slotmachine.setY(screen_height/2-bttn_slotmachine.getHeight()/2+pad);


        s = new Sprite(new Texture("menu/bttn_blackjack.png"));
        s.setSize(screen_width/4, scale*s.getHeight());
        bttn_blackjack = new ImageButton(new SpriteDrawable(s));
        bttn_blackjack.setX(screen_width/2-bttn_blackjack.getWidth()/2+pad);
        bttn_blackjack.setY(screen_height/2-bttn_blackjack.getHeight()/2+pad);

        s = new Sprite(new Texture("menu/bttn_roulette.png"));
        s.setSize(screen_width/4, scale*s.getHeight());
        bttn_roulette = new ImageButton(new SpriteDrawable(s));
        bttn_roulette.setX(screen_width/2-bttn_roulette.getWidth()/2+pad);
        bttn_roulette.setY(screen_height/2-bttn_roulette.getHeight()/2-pad);

        s = new Sprite(new Texture("menu/bttn_poker.png"));
        s.setSize(screen_width/4, scale*s.getHeight());
        bttn_poker = new ImageButton(new SpriteDrawable(s));
        bttn_poker.setX(screen_width/2-bttn_poker.getWidth()/2-pad);
        bttn_poker.setY(screen_height/2-bttn_poker.getHeight()/2-pad);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);
        stage.addActor(bttn_slotmachine);
        stage.addActor(bttn_blackjack);
        stage.addActor(bttn_roulette);
        stage.addActor(bttn_poker);

        bttn_slotmachine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenSlotMachine(sm));
            }
        });

        bttn_blackjack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new StateBJack(sm));
            }
        });

        bttn_roulette.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenRoulette(sm));
            }
        });

        bttn_poker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //sm.add(new StateBJack(sm));
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
