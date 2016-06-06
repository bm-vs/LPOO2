package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Roulette;


public class ScreenRoulette extends com.mygdx.game.GUI.ScreenState {

    public Stage stage;
    private Animator roulette_wheel;
    private Animator roulette_ball;
    private ImageButton bttn_roll;
    private ImageButton bttn_bet;
    private Image win;
    private Roulette roulette;
    private long t_start;
    private int i = 1;
    private int result;
    private Integer[] bet;
    private boolean bet_set;
    private boolean positioning;

    public ScreenRoulette(ScreenManager sm) {
        super(sm);
        bet_set = false;
        create();
    }

    public ScreenRoulette(ScreenManager sm, Integer[] bet) {
        super(sm);
        this.bet = bet;
        bet_set = true;
        create();
    }


    @Override
    public void create() {
        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();

        stage = new Stage(new ScreenViewport());
        roulette = new Roulette();
        positioning = true;

        //================================================================================================================================================================
        // BUTTON ROLL
        Sprite s = new Sprite(new Texture("roulette/bttn_roll.png"));
        float scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("roulette/bttn_d_roll.png"));
        sd.setSize(screen_width/4, scale*sd.getHeight());

        bttn_roll = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_roll.setX(screen_width/2-bttn_roll.getWidth()/2);
        bttn_roll.setY(screen_height/5-bttn_roll.getHeight());


        //================================================================================================================================================================
        // WIN TEXT
        win = new Image(new Texture("roulette/win.png"));
        scale = (screen_width/4)/win.getWidth();
        win.setSize(screen_width/4, scale*win.getHeight());
        win.setX(screen_width/2-win.getWidth()/2);
        win.setY(screen_height/5-win.getHeight());


        //================================================================================================================================================================
        // BUTTON BET
        s = new Sprite(new Texture("roulette/bttn_bet.png"));
        scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bttn_d_bet.png"));
        sd.setSize(screen_width/4, scale*sd.getHeight());

        bttn_bet = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_bet.setX(screen_width/2-bttn_bet.getWidth()/2);
        bttn_bet.setY(screen_height/5+bttn_bet.getHeight()/2);


        //===============================================================================================================
        // ROULETTE WHEEL
        Image[] w = new Image[1];
        w[0] = new Image(new Texture("roulette/wheel.png"));
        roulette_wheel = new Animator(w);
        scale = (screen_width*4/5)/roulette_wheel.getWidth();
        roulette_wheel.setSize(screen_width*4/5, scale*roulette_wheel.getHeight());
        roulette_wheel.setX(screen_width/2-roulette_wheel.getWidth()/2);
        roulette_wheel.setY(screen_height/2-roulette_wheel.getHeight()/4);


        //===============================================================================================================
        // ROULETTE BALL
        Image[] b = new Image[1];
        b[0] = new Image(new Texture("roulette/ball.png"));
        roulette_ball = new Animator(b);
        roulette_ball.setSize(scale*roulette_ball.getWidth(), scale*roulette_ball.getHeight());
        roulette_ball.setX(screen_width/2-roulette_ball.getWidth()/2);
        roulette_ball.setY(roulette_wheel.getY());


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(roulette_wheel);
        stage.addActor(roulette_ball);
        if (bet_set) {
            stage.addActor(bttn_roll);
        }
        stage.addActor(bttn_bet);

        bttn_roll.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!bet_set) {
                    return;
                }

                roulette_wheel.setSpinning(true);
                roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY()));
                result = roulette.roll();
                roulette_wheel.setAngle(180+result*360/37);
                t_start = TimeUtils.millis();
                bet_set = false;
                bttn_roll.remove();

                System.out.println(roulette.getCurrentNumber().getNumber());
            }
        });

        bttn_bet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.remove();
                sm.add(new ScreenBet(sm));
            }
        });


    }

    @Override
    public void render() {
        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();

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
            int return_value = roulette.betReturn(1, bet);
            if (return_value > 0 && win.getStage() == null) {
                stage.addActor(win);
                System.out.println(roulette_ball.getAngle());
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
