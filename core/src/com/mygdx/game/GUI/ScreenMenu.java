package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Player;


public class ScreenMenu extends ScreenState {

    public Stage stage;
    private ImageButton bttn_slotmachine;
    private ImageButton bttn_blackjack;
    private ImageButton bttn_roulette;
    private ImageButton bttn_earnings;
    private Label player_money;

    public ScreenMenu(ScreenManager sm, Player P) {
        super(sm,P);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        float pad = WIDTH/6;


        int n = (int) Math.floor(P.getMoney());
        player_money = new Label(new String(Integer.toString(n)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        float scale = (WIDTH/16)/player_money.getHeight();
        player_money.setFontScale(scale);
        player_money.setX(WIDTH - WIDTH/6);
        player_money.setY(HEIGHT - scale*player_money.getHeight());



        Sprite s = new Sprite(new Texture("menu/bttn_slotmachine.png"));
        scale = (WIDTH/4)/s.getWidth();
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
        bttn_earnings = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_earnings.setX(WIDTH/2-bttn_earnings.getWidth()/2-pad);
        bttn_earnings.setY(HEIGHT/2-bttn_earnings.getHeight()/2-pad);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(bttn_slotmachine);
        stage.addActor(bttn_blackjack);
        stage.addActor(bttn_roulette);
        stage.addActor(bttn_earnings);
        stage.addActor(player_money);

        bttn_slotmachine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenSlotMachine(sm, P));
            }
        });

        bttn_blackjack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenBlackjack(sm, P));
            }
        });

        bttn_roulette.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenRoulette(sm, P));
            }
        });

        bttn_earnings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenEarnings(sm, P));
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        Gdx.input.setInputProcessor(stage);
        int n = (int) Math.floor(P.getMoney());
        player_money.setText(new String(Integer.toString(n)));
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
