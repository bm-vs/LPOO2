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
import java.util.Random;

public class ScreenRoulette extends ScreenState {

    public Stage stage;
    private Animator roulette_wheel;
    private Animator roulette_ball;
    private ImageButton bttn_play;
    private long t_start;
    private int i = 1;

    public ScreenRoulette(ScreenManager sm) {
        super(sm);
        create();
    }


    @Override
    public void create() {
        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();

        stage = new Stage(new ScreenViewport());


        //================================================================================================================================================================
        // BUTTON PLAY
        Sprite s = new Sprite(new Texture("slot_machine/bttn_play.png"));
        float scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());

        bttn_play = new ImageButton(new SpriteDrawable(s));
        bttn_play.setX(screen_width/2-bttn_play.getWidth()/2);
        bttn_play.setY(screen_height/5-bttn_play.getHeight()/2);


        //===============================================================================================================
        // ROULETTE WHEEL
        Image[] w = new Image[1];
        w[0] = new Image(new Texture("roulette/wheel.png"));

        roulette_wheel = new Animator(w);
        scale = (screen_width*4/5)/roulette_wheel.getWidth();
        roulette_wheel.setSize(screen_width*4/5, scale*roulette_wheel.getHeight());
        roulette_wheel.setX(screen_width/2-roulette_wheel.getWidth()/2);
        roulette_wheel.setY(screen_height/2-roulette_wheel.getHeight()/3);


        //===============================================================================================================
        // ROULETTE BALL
        Image[] b = new Image[1];
        b[0] = new Image(new Texture("roulette/ball.png"));
        roulette_ball = new Animator(b);
        roulette_ball.setSize(scale*roulette_ball.getWidth(), scale*roulette_ball.getHeight());
        roulette_ball.setX(screen_width/2-roulette_ball.getWidth()/2);
        roulette_ball.setY(screen_height/2-roulette_wheel.getHeight()/3);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(roulette_wheel);
        stage.addActor(roulette_ball);
        stage.addActor(bttn_play);

        bttn_play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                roulette_wheel.setSpinning(true);
                roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY()));
                t_start = TimeUtils.millis();
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
        int start = 3000; int end = 4000;

        if (TimeUtils.millis()-t_start > start && TimeUtils.millis()-t_start < end && roulette_wheel.getSpinning()) {
            roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY())-i*animation_step*roulette_wheel.getHeight());
            roulette_ball.setY(screen_height/2-roulette_wheel.getHeight()/3+i*animation_step*roulette_wheel.getHeight());
            roulette_ball.angleInc(-1);
            roulette_wheel.angleInc(1);
            roulette_wheel.rotSpeedDec();
            i++;
        }
        else if (i > 1 && roulette_wheel.getSpinning()) {
            roulette_ball.setOrigin(roulette_ball.getWidth()/2, roulette_wheel.getHeight()/2-(roulette_ball.getY()-roulette_wheel.getY()));
            roulette_ball.angleInc(1);
            roulette_ball.rotSpeedDec();
            roulette_wheel.angleInc(1);
            roulette_wheel.rotSpeedDec();
        }
        else if (roulette_wheel.getSpinning()) {
            roulette_ball.angleInc(-1);
            roulette_wheel.angleInc(1);
            roulette_wheel.rotSpeedDec();
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
