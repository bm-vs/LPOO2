package com.mygdx.game.State;

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

public class Menu extends StateBase {


    private Texture casino;
    private Texture play;
    private Texture start;
    private SpriteBatch batch;

    public Stage stage;
    public Skin skin;
    TextButton bttnSlot;
    TextButton bttnBack;
    BitmapFont font;
    Boolean muda;
    Texture pixmapTexture;



    public Menu(ScreenManeger sm) {
        super(sm);
        create();
    }

    @Override
    protected void create() {

        casino = new Texture("casino.png");
        play = new Texture("play.jpg");
        start = new Texture ("inicio.png");

        muda = new Boolean(false);
        batch = new SpriteBatch();
        skin = new Skin();
        stage = new Stage();


        font = new BitmapFont();
        skin.add("default", font);


        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGBA8888);//cor das letras
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        pixmapTexture = new Texture(pixmap);
        skin.add("background", pixmapTexture);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.down = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.checked = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("background", Color.CYAN);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        bttnSlot = new TextButton("SLot Machine", skin);
        bttnSlot.getLabel().setFontScale(2, 2);


        bttnBack = new TextButton("BlackJack", skin);
        bttnBack.getLabel().setFontScale(2, 2);



        Table buttonsTable = new Table();
        buttonsTable.add(bttnBack).width(Gdx.graphics.getWidth() / 2).padBottom(50);
        buttonsTable.row();
        buttonsTable.add(bttnSlot).width(Gdx.graphics.getWidth() / 2).padBottom(50);
        buttonsTable.row();
        buttonsTable.setFillParent(true);

        buttonsTable.top().padTop(Gdx.graphics.getHeight() / 4);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);

        bttnSlot.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("slot");
                sm.add(new StateSMachine(sm));
            }
        });

        bttnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back");
                sm.add(new StateBJack(sm));
                //sm.add(new EndGame(sm));

            }
        });
    }


    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isTouched())
            muda = true;

    }

    @Override
    public void render() {


        if (muda) {
            Gdx.gl.glClearColor(1, 1,1,1);
            batch.begin();
            batch.draw(start, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            batch.end();

            Gdx.input.setInputProcessor(stage);
            stage.act();
            stage.draw();
        } else
        {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            batch.begin();
            batch.draw(casino, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }
    }

    @Override
    public void dispose() {

        casino.dispose();
        play.dispose();
        start.dispose();

        stage.dispose();
        skin.dispose();
        font.dispose();
        start.dispose();
        pixmapTexture.dispose();

    }


}
