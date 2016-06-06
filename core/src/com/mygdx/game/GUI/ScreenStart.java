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


public class ScreenStart extends ScreenState {
    public Stage stage;

    public ScreenStart(ScreenManager sm) {
        super(sm);
        create();
    }

    @Override
    public void create() {
        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();

        stage = new Stage(new ScreenViewport());

        //===============================================================================================================
        // START BUTTON

        Sprite s = new Sprite(new Texture("start/bttn_start.png"));
        float scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());

        ImageButton bttn_start = new ImageButton(new SpriteDrawable(s));
        bttn_start.setTransform(true);
        bttn_start.setX(screen_width/2-bttn_start.getWidth()/2);
        bttn_start.setY(screen_height/2-bttn_start.getHeight()/2 - screen_height/8);

        //===============================================================================================================
        // CASINO IMAGE

        Image casino_image = new Image(new Texture("start/img_casino.png"));
        scale = (screen_width/2)/casino_image.getWidth();
        casino_image.setSize(screen_width/2, scale*casino_image.getHeight());
        casino_image.setX(screen_width/2-casino_image.getWidth()/2);
        casino_image.setY(screen_height/2-casino_image.getHeight()/2 + screen_height/8);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(bttn_start);
        stage.addActor(casino_image);

        bttn_start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.add(new ScreenMenu(sm));
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
