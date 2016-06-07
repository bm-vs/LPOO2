package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Player;
import com.mygdx.game.Logic.SlotMachine;

public class ScreenEarnings extends com.mygdx.game.GUI.ScreenState {

    public Stage stage;
    private ImageButton bttn_reset;
    private Label money;
    private Label es;
    private Label er;
    private Label eb;

    public ScreenEarnings(ScreenManager sm, Player P) {
        super(sm, P);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());

        money = new Label(Integer.toString((int) Math.floor(P.getMoney())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        float scale = (WIDTH/14)/money.getHeight();
        money.setFontScale(scale);
        es = new Label(Integer.toString((int) Math.floor(P.getEarningsSlot())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        es.setFontScale(scale);
        er = new Label(Integer.toString((int) Math.floor(P.getEarningsRoulette())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        er.setFontScale(scale);
        eb = new Label(Integer.toString((int) Math.floor(P.getEarningsBlackjack())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        eb.setFontScale(scale);


        Label s_money = new Label("Current money", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        s_money.setFontScale(scale);
        Label s_es = new Label("Slots total earnings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        s_es.setFontScale(scale);
        Label s_er = new Label("Roulette total earnings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        s_er.setFontScale(scale);
        Label s_eb = new Label("Blackjack total earnings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        s_eb.setFontScale(scale);


        float pad = s_eb.getHeight();

        Table table = new Table();
        table.add(s_money).left().pad(pad).padRight(pad*2);
        table.add(money).left().pad(pad);
        table.row();
        table.add(s_es).left().pad(pad).padRight(pad*2);
        table.add(es).left().pad(pad);
        table.row();
        table.add(s_er).left().pad(pad).padRight(pad*2);
        table.add(er).left().pad(pad);
        table.row();
        table.add(s_eb).left().pad(pad).padRight(pad*2);
        table.add(eb).left().pad(pad);

        table.setX(WIDTH/2);
        table.setY(HEIGHT/2);

        Sprite s = new Sprite(new Texture("stats/reset.png"));
        s.setSize(WIDTH/4, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("stats/reset_d.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());
        bttn_reset = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_reset.setX(WIDTH/2-bttn_reset.getWidth()/2);
        bttn_reset.setY(table.getY()/2 - bttn_reset.getHeight()/2);


        Image i = new Image (new Texture("stats/stats_image.png"));
        scale = (WIDTH/2)/i.getWidth();
        i.setSize(WIDTH/2, scale*i.getHeight());
        i.setX(WIDTH/2-i.getWidth()/2);
        i.setY(HEIGHT-(HEIGHT-table.getY())/2+i.getHeight()/2);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(table);
        stage.addActor(bttn_reset);
        stage.addActor(i);

        bttn_reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                P.setMoney(100);
                P.addEarningsSlot(-P.getEarningsSlot());
                P.addEarningsBlackjack(-P.getEarningsBlackjack());
                P.addEarningsRoulette(-P.getEarningsRoulette());

                money.setText(Integer.toString((int) Math.floor(P.getMoney())));
                es.setText(Integer.toString((int) Math.floor(P.getEarningsSlot())));
                er.setText(Integer.toString((int) Math.floor(P.getEarningsRoulette())));
                eb.setText(Integer.toString((int) Math.floor(P.getEarningsBlackjack())));
            }
        });

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        stage.act();
        stage.draw();

        // WHEN BACK BUTTON IS PRESSED RETURN TO MENU
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            sm.remove();
        }
    }

    @Override
    protected void handleInput() {}

    @Override
    public void update(float dt) {}

    @Override
    public void dispose() {
        stage.dispose();
    }











}