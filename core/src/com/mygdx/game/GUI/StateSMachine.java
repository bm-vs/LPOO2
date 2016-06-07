package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Logic.Player;


public class StateSMachine extends ScreenState {

    private Texture slot;
    private SpriteBatch batch;
    public Stage stage;
    public Skin skin;
    TextButton play;
    TextButton exit;
    BitmapFont font;
    Texture pixmapTexture;



    protected StateSMachine(ScreenManager sm, Player P) {
        super(sm, P);
        create();
    }

    @Override
    public void create() {
        slot = new Texture("slotMachine.png");
        batch = new SpriteBatch();
        stage = new Stage();
        font = new BitmapFont();

        skin = new Skin();


        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGBA8888);//cor das letras
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        // Creating button styles
        pixmapTexture = new Texture(pixmap);
        skin.add("back", pixmapTexture);
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("back", Color.YELLOW);
        textButtonStyle.down = skin.newDrawable("back", Color.YELLOW);
        textButtonStyle.checked = skin.newDrawable("back", Color.YELLOW);
        textButtonStyle.over = skin.newDrawable("back", Color.ORANGE);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        //=============

        Texture pixmapTexture2 = new Texture(pixmap);
        skin.add("play", pixmapTexture2);
        skin.add("background", font);

        TextButton.TextButtonStyle StylePlay = new TextButton.TextButtonStyle();
        StylePlay.up = skin.newDrawable("play", Color.BLACK);
        StylePlay.down = skin.newDrawable("play", Color.BLACK);
        StylePlay.checked = skin.newDrawable("play", Color.YELLOW);
        StylePlay.over = skin.newDrawable("play", Color.YELLOW);
        StylePlay.font = skin.getFont("background");
        skin.add("background", StylePlay);

//============
        exit = new TextButton("Back", skin);
        exit.getLabel().setFontScale(2, 2);

        play = new TextButton("Play", skin, "background");
        play.getLabel().setFontScale(2, 2);

          Table buttonsTable = new Table();
        buttonsTable.add(play).width(Gdx.graphics.getWidth() / 2).padBottom(5);//espaço entre os buttoes
       /* buttonsTable.row();
        buttonsTable.add(exit).width(Gdx.graphics.getWidth() / 2).padBottom(50);
        buttonsTable.row();*/
        buttonsTable.setFillParent(true);

        exit.setPosition(Gdx.graphics.getWidth()-200,0);
        stage.addActor(exit);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);


        exit.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event,float x, float y){
            System.out.println("Back");
            sm.remove();

        }
        });

        play.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("play slot");

            }
        });
    }



    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        batch.begin();
        batch.draw(slot, 0, 0, Gdx.graphics.getWidth()-10, Gdx.graphics.getHeight()-10);
        batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {

        stage.dispose();
        skin.dispose();
        slot.dispose();
        font.dispose();
        pixmapTexture.dispose();

    }

}
