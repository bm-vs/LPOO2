package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Player;
import com.mygdx.game.Logic.Roulette;


public class ScreenRoulette extends com.mygdx.game.GUI.ScreenState {

    public Stage stage;
    private Animator roulette_wheel;
    private Animator roulette_ball;
    private ImageButton bttn_roll;
    private ImageButton bttn_bet;
    private Image win;
    private Image lost;
    private Roulette roulette;
    private long t_start;
    private int i = 1;
    private int result;
    private Integer[] bet;
    private boolean bet_set;
    private boolean positioning;
    private int bet_value;
    private Label player_money;

    public ScreenRoulette(ScreenManager sm, Player P) {
        super(sm, P);
        bet_set = false;
        create();
    }

    public ScreenRoulette(ScreenManager sm, Player P, Integer[] bet, int bet_value) {
        super(sm, P);
        this.bet = bet;
        this.bet_value = bet_value;
        bet_set = true;
        create();
    }


    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        roulette = new Roulette();
        positioning = true;

        int n = (int) Math.floor(P.getMoney());
        player_money = new Label(new String(Integer.toString(n)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        float scale = (WIDTH/16)/player_money.getHeight();
        player_money.setFontScale(scale);
        player_money.setX(WIDTH - WIDTH/6);
        player_money.setY(HEIGHT - 2*scale*player_money.getHeight());


        //================================================================================================================================================================
        // BUTTON ROLL
        Sprite s = new Sprite(new Texture("roulette/bttn_roll.png"));
        scale = (WIDTH/4)/s.getWidth();
        s.setSize(WIDTH/4, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("roulette/bttn_d_roll.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());

        bttn_roll = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_roll.setX(WIDTH/2-bttn_roll.getWidth()/2);
        bttn_roll.setY(HEIGHT/5-bttn_roll.getHeight());


        //================================================================================================================================================================
        // WIN TEXT
        win = new Image(new Texture("roulette/win.png"));
        scale = (WIDTH/4)/win.getWidth();
        win.setSize(WIDTH/4, scale*win.getHeight());
        win.setX(WIDTH/2-win.getWidth()/2);
        win.setY(HEIGHT/5-win.getHeight());

        //================================================================================================================================================================
        // LOST TEXT
        lost = new Image(new Texture("roulette/lost.png"));
        scale = (WIDTH/4)/lost.getWidth();
        lost.setSize(WIDTH/4, scale*lost.getHeight());
        lost.setX(WIDTH/2-lost.getWidth()/2);
        lost.setY(HEIGHT/5-lost.getHeight());


        //================================================================================================================================================================
        // BUTTON BET
        s = new Sprite(new Texture("roulette/bttn_bet.png"));
        scale = (WIDTH/4)/s.getWidth();
        s.setSize(WIDTH/4, scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bttn_d_bet.png"));
        sd.setSize(WIDTH/4, scale*sd.getHeight());

        bttn_bet = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_bet.setX(WIDTH/2-bttn_bet.getWidth()/2);
        bttn_bet.setY(HEIGHT/5+bttn_bet.getHeight()/2);


        //===============================================================================================================
        // ROULETTE WHEEL
        Image[] w = new Image[1];
        w[0] = new Image(new Texture("roulette/wheel.png"));
        roulette_wheel = new Animator(w);
        scale = (WIDTH*4/5)/roulette_wheel.getWidth();
        roulette_wheel.setSize(WIDTH*4/5, scale*roulette_wheel.getHeight());
        roulette_wheel.setX(WIDTH/2-roulette_wheel.getWidth()/2);
        roulette_wheel.setY(HEIGHT/2-roulette_wheel.getHeight()/4);


        //===============================================================================================================
        // ROULETTE BALL
        Image[] b = new Image[1];
        b[0] = new Image(new Texture("roulette/ball.png"));
        roulette_ball = new Animator(b);
        roulette_ball.setSize(scale*roulette_ball.getWidth(), scale*roulette_ball.getHeight());
        roulette_ball.setX(WIDTH/2-roulette_ball.getWidth()/2);
        roulette_ball.setY(roulette_wheel.getY());


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(roulette_wheel);
        stage.addActor(roulette_ball);
        stage.addActor(player_money);
        if (bet_set) {
            stage.addActor(bttn_roll);
        }
        else {
            stage.addActor(bttn_bet);
        }

        bttn_roll.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!bet_set) {
                    return;
                }

                int n = (int) Math.floor(P.getMoney());
                player_money.setText(new String(Integer.toString(n)));

                roulette_wheel.setSpinning(true);
                roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY()));
                result = roulette.roll();
                roulette_wheel.setAngle(180+result*360/37);
                t_start = TimeUtils.millis();
                bet_set = false;
                bttn_roll.remove();
            }
        });

        bttn_bet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(P.getMoney() == 0) {
                    P.setMoney(150);
                }

                sm.remove();
                sm.add(new ScreenBet(sm, P));
            }
        });


    }

    @Override
    public void render() {
        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();

        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        stage.act();

        float animation_step = 0.00375f;
        long start = 3000; int end = 4000;


        if (TimeUtils.millis()-t_start > start && roulette_ball.getAngle() > -720 && roulette_wheel.getSpinning() && positioning) {
            roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY())-i*animation_step*roulette_wheel.getHeight());
            roulette_ball.setY(roulette_wheel.getY()+i*animation_step*roulette_wheel.getHeight());
            roulette_ball.angleInc(-1);
            roulette_wheel.angleInc(1);

            i++;
        }
        else if (i > 1 && roulette_wheel.getSpinning()) {
            positioning = false;
            int return_value = roulette.betReturn(bet_value, bet);

            if (return_value > 0 && win.getStage() == null) {
                stage.addActor(win);

                P.setMoney(P.getMoney() + return_value);
                P.addEarningsRoulette(return_value);
                int n = (int) Math.floor(P.getMoney());
                player_money.setText(new String(Integer.toString(n)));
            }

            if (return_value == 0 && lost.getStage() == null) {
                stage.addActor(lost);
            }

            if (bttn_bet.getStage() == null) {
                stage.addActor(bttn_bet);
            }

            roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY()));
            roulette_ball.angleInc(1);
            roulette_ball.rotSpeedDec();
            roulette_wheel.angleInc(1);
            roulette_wheel.rotSpeedDec();

            if (roulette_wheel.getRotSpeed() == 0) {
                roulette_wheel.setSpinning(false);
            }

        }
        else if (roulette_wheel.getSpinning()) {
            roulette_ball.angleInc(-1);
            roulette_wheel.angleInc(1);
        }

        // WHEN BACK BUTTON IS PRESSED RETURN TO MENU
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            sm.remove();
        }

        stage.draw();
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
